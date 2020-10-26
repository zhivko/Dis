package si.telekom.dis.client.action;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.Scheduler;
import com.google.gwt.core.client.Scheduler.ScheduledCommand;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.TabPanel;

import si.telekom.dis.client.ExplorerPanel;
import si.telekom.dis.client.MainPanel;
import si.telekom.dis.client.MenuPanel;
import si.telekom.dis.client.ParametrizedQueryPanel;
import si.telekom.dis.client.WindowBox;
import si.telekom.dis.client.item.FormAttribute;
import si.telekom.dis.shared.Attribute;
import si.telekom.dis.shared.ExplorerService;
import si.telekom.dis.shared.ExplorerServiceAsync;
import si.telekom.dis.shared.ProfileAttributesAndValues;
import si.telekom.dis.shared.Tab;

public class DocumentProperties extends WindowBox {
	String r_object_id;
	private final static ExplorerServiceAsync explorerService = GWT.create(ExplorerService.class);
	private final static Logger logger = java.util.logging.Logger.getLogger("mylogger");
	TabPanel tpAtts;
	ArrayList<FormAttribute> allFaAl = new ArrayList<FormAttribute>();
	static DocumentProperties instance;

	public DocumentProperties(String r_object_id_) {
		instance = this;

		r_object_id = r_object_id_;
		setText("Lastnosti");
		setGlassEnabled(true);

		tpAtts = new TabPanel();
		tpAtts.setWidth(getMaxWidthPx());

		ScrollPanel sp = new ScrollPanel(tpAtts);
		sp.setWidth(getMaxWidthPx());
		sp.setHeight(getMaxHeightPx());

		Scheduler.get().scheduleDeferred(new ScheduledCommand() {
			@Override
			public void execute() {
				DocumentProperties.this.center();
			}
		});

		MainPanel.log("Nalagam atribute..");
		explorerService.getProfileAttributesAndValues(MainPanel.getInstance().loginName,
				MainPanel.getInstance().loginPass, r_object_id_, new AsyncCallback<ProfileAttributesAndValues>() {

					@Override
					public void onSuccess(ProfileAttributesAndValues result) {
						// generate tabs
						for (Tab tab : result.profile.tabs) {
							Grid g = new Grid(tab.row, tab.col);
							getOkButton().setEnabled(false);
							for (Attribute att : result.attributes) {
								if (tab.getId().equals(att.tabId)) {
									// logger.info(att.dcmtAttName);
									FormAttribute fa = new FormAttribute(att);
									fa.setWidth("90%");
									fa.addValueChangeHandler(new ValueChangeHandler<List<String>>() {
										public void onValueChange(
												com.google.gwt.event.logical.shared.ValueChangeEvent<java.util.List<String>> event) {
											if (checkMandatoryAttributes())
												getOkButton().setEnabled(true);

											fillDependendAttributes(fa.att.dcmtAttName);
										};
									});
									List<String> values = result.values.get(att.dcmtAttName);
									if (values != null) {
										fa.setValue(values);
									} else {
										MainPanel.log("Attribute value for attribute <strong>" + att.dcmtAttName
												+ "</strong> not received from server.");
										fa.att.isReadOnly = true;
									}
									g.setWidget(att.row, att.col, fa);
									allFaAl.add(fa);
									// logger.info("\tadded to row:" + att.row + " col:" + att.col);
								}
							}
							tpAtts.add(g, tab.getParameter());
						}
						tpAtts.selectTab(0);
						if (checkMandatoryAttributes())
							getOkButton().setEnabled(true);

						Scheduler.get().scheduleDeferred(new ScheduledCommand() {
							@Override
							public void execute() {
								DocumentProperties.this.center();
							}
						});
					}

					@Override
					public void onFailure(Throwable caught) {
						logger.info(caught.getMessage());
					}
				});

		getOkButton().addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				List<String[]> values = getValues();
				explorerService.setAttributes(MainPanel.getInstance().loginName, MainPanel.getInstance().loginPass,
						r_object_id_, values, new AsyncCallback<Void>() {
							@Override
							public void onFailure(Throwable caught) {
								MainPanel.log(caught.getMessage());
							}

							@Override
							public void onSuccess(Void result) {
								MainPanel.log("Profile attributes written to object succesfully.");
								MenuPanel.activeExplorerInstance.refreshLastSelectedNode();
								DocumentProperties.this.hide(true);
							}
						});
			}
		});
		getContentPanel().add(sp);
	}

	protected void fillDependendAttributes(String dcmtAttName) {
		MainPanel.log("Računam odvisne atributov...");
		for (FormAttribute fa : allFaAl) {
			if (fa.att.dependsOn != null && !fa.att.dependsOn.equals("")) {
				String dpds = fa.att.dependsOn;
				String dependsonAtt = split(fa.att.dependsOn, ",")[0];
				int columnIndex = Integer.valueOf(split(fa.att.dependsOn, ",")[1]);
				FormAttribute dependsOnAtt = findFormAttribute(dependsonAtt);
				String fullRow = dependsOnAtt.getFirstMultiselectRow();
				String value = split(fullRow, "|")[columnIndex];
				ArrayList<String> values = new ArrayList<String>();
				values.add(value);
				fa.setValue(values);
			}
		}
	}

	FormAttribute findFormAttribute(String dctmAttName) {
		for (FormAttribute fa : allFaAl) {
			if (fa.att.dcmtAttName.equalsIgnoreCase(dctmAttName)) {
				return fa;
			}

		}
		return null;
	}

	public boolean checkMandatoryAttributes() {
		boolean ret = true;
		MainPanel.log("Preverjam obvezne atribute...");
		for (FormAttribute fa : allFaAl) {
			fa.mandatoryOk();
			if (fa.att.isMandatory) {
				String val = fa.getValue();
				if (val.equals("")) {
					MainPanel.log("Obvezni atribut " + fa.att.dcmtAttName + " ni izbran.");
					fa.mandatoryNeeded();
					ret = false;
				}
			}
		}
		return ret;
	}

	
	public List<String[]> getValues()
	{
		ArrayList<String[]> ret = new ArrayList<String[]>();
		for (int i = 0; i < tpAtts.getTabBar().getTabCount(); i++) {
			Grid g = (Grid) tpAtts.getWidget(i);
			for (int j = 0; j < g.getRowCount(); j++)
				for (int k = 0; k < g.getColumnCount(); k++) {
					if (g.getWidget(j, k) != null) {
						FormAttribute fa = (FormAttribute) g.getWidget(j, k);
						String[] value = { fa.getAtribute().dcmtAttName, null };
						if (!fa.att.isRepeating)
							value[1] = split(fa.getValue(), "|")[fa.att.dropDownCol];
						else {
							String allValues = "";
							for (String val : fa.getValues()) {
								allValues = allValues + split(val, "|")[fa.att.dropDownCol] + "¨";
							}
							if (allValues.endsWith("¨"))
								allValues = allValues.substring(0, allValues.length() - 1);
							value[1] = allValues;
						}
						ret.add(value);
					}
				}
		}
		return ret;
	}
	
	
	public String getAttributeValue(String attName)
	{
		List<String[]> values = getValues();
		for (String[] strings : values) {
			if(strings[0].equals(attName))
				return strings[1];
		}
		return null;
	}
	
	public static DocumentProperties getInstance()
	{
		return instance;
	}
	
	
	public static final native String[] split(String string, String separator) /*-{
		return string.split(separator);
	}-*/;
}
