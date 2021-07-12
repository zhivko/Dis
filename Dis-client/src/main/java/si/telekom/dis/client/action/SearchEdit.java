package si.telekom.dis.client.action;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Logger;

import com.google.gwt.cell.client.FieldUpdater;
import com.google.gwt.cell.client.TextInputCell;
import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.Scheduler;
import com.google.gwt.core.client.Scheduler.ScheduledCommand;
import com.google.gwt.dom.client.Style.BorderStyle;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.regexp.shared.MatchResult;
import com.google.gwt.regexp.shared.RegExp;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.DataGrid;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.VerticalPanel;

import si.telekom.dis.client.MainPanel;
import si.telekom.dis.client.MenuPanel;
import si.telekom.dis.client.MyTextArea;
import si.telekom.dis.client.MyTxtBox;
import si.telekom.dis.client.ParametrizedQueryPanel;
import si.telekom.dis.client.WindowBox;
import si.telekom.dis.client.item.FormAttribute;
import si.telekom.dis.shared.AdminService;
import si.telekom.dis.shared.AdminServiceAsync;
import si.telekom.dis.shared.Attribute;
import si.telekom.dis.shared.DcmtAttribute;
import si.telekom.dis.shared.DocType;
import si.telekom.dis.shared.MyParametrizedQuery;
import si.telekom.dis.shared.ServerException;
import si.telekom.dis.shared.UserGroup;

public class SearchEdit extends WindowBox {
	private final static AdminServiceAsync adminService = GWT.create(AdminService.class);
	private final static Logger logger = java.util.logging.Logger.getLogger("mylogger");

	MyTextArea dql;
	MyTxtBox name;
	MyTxtBox filterClass;
	public DataGrid<ParameterLabel> datagrid;

	String oldName;

	FormAttribute faGroups;

	ParametrizedQueryPanel pqp;

	FormAttribute faSortAtts;

	HorizontalPanel hp;

	ListBox lbSortOrder;

	ArrayList<String> orderByDirections;

	public Button duplicateSearch;
	public Button deleteSearch;

	public SearchEdit(ParametrizedQueryPanel parametrizedQueryPanel) {
		orderByDirections = new ArrayList<String>();
		hp = new HorizontalPanel();
		getContentPanel().add(hp);
		this.pqp = parametrizedQueryPanel;

		oldName = this.pqp.parametrizedQuery.name;
		setText("Edit Search");
		setGlassEnabled(true);

		name = new MyTxtBox("Search name");
		name.setValue(this.pqp.parametrizedQuery.name);
		name.setTextBoxWidth("700px");

		getContentPanel().add(name);
		dql = new MyTextArea("Parametrized dql");
		dql.setValue(this.pqp.parametrizedQuery.dql);
		dql.setTextAreaWidth("700px");
		hp.add(dql);
		if (MainPanel.getInstance().loginRole.toLowerCase().equals("administrator")) {
			dql.setIsEditable(true);
			name.setIsEditable(true);
		} else {
			dql.setIsEditable(false);
			name.setIsEditable(false);
		}
		dql.setTextAreaHeight("350px");
		datagrid = new DataGrid<ParameterLabel>(10);
		datagrid.setVisibleRange(0, 10);

		// userId Column
		Column<ParameterLabel, String> parameter = new Column<ParameterLabel, String>(new TextInputCell()) {
			public String getValue(ParameterLabel object) {
				return object.parameter;
			}
		};

		Column<ParameterLabel, String> label = new Column<ParameterLabel, String>(new TextInputCell()) {
			public String getValue(ParameterLabel object) {
				return object.label;
			}
		};

		label.setFieldUpdater(new FieldUpdater<SearchEdit.ParameterLabel, String>() {
			@Override
			public void update(int index, ParameterLabel object, String value) {
				List<String> pars = new ArrayList<String>();
				object.label = value;
				Iterator<ParameterLabel> it = SearchEdit.this.datagrid.getVisibleItems().listIterator();
				while (it.hasNext()) {
					ParameterLabel parLab = it.next();
					pars.add(parLab.label);
				}

				try {
					adminService.updateParameterLabelForParametrizedQuery(MainPanel.getInstance().loginName, MainPanel.getInstance().loginPass,
							name.getTextBox().getValue(), pars, new AsyncCallback<Void>() {
								@Override
								public void onSuccess(Void result) {
									MainPanel.log("Done updating label for parameter: " + object.parameter + " label: " + object.label);
								}

								@Override
								public void onFailure(Throwable caught) {
									MainPanel.log(caught.getMessage());
								}
							});
				} catch (ServerException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});

		datagrid.addColumn(parameter, "Parameter");
		datagrid.addColumn(label, "Label");
		datagrid.setColumnWidth(parameter, "100px");
		datagrid.setColumnWidth(label, "200px");
		datagrid.setHeight("200px");
		datagrid.getElement().getStyle().setBorderStyle(BorderStyle.SOLID);
		datagrid.getElement().getStyle().setBorderWidth(0.5, Unit.PX);
		datagrid.getElement().getStyle().setBorderColor("#000000");

		ArrayList<ParameterLabel> al = new ArrayList<ParameterLabel>();
		for (int i = 0; i < this.pqp.parametrizedQuery.labels.size(); i++) {
			al.add(new ParameterLabel("<" + i + ">", this.pqp.parametrizedQuery.labels.get(i)));
		}
		datagrid.setRowData(0, al);
		datagrid.setVisibleRange(0, al.size());

		dql.getTextBox().addKeyUpHandler(new KeyUpHandler() {
			@Override
			public void onKeyUp(KeyUpEvent event) {
				parseDqlAndGetLabels();
			}
		});

		filterClass = new MyTxtBox("Filter class name");
		filterClass.setValue(this.pqp.parametrizedQuery.filterClass);
		filterClass.setTextBoxWidth("700px");
		getContentPanel().add(filterClass);

		Attribute aAtts = new Attribute();
		aAtts.isRepeating = true;
		aAtts.label = "Razvrstitev";
		aAtts.isLimitedToValueList = true;
		aAtts.setType(Attribute.types.DROPDOWN.type);
		faSortAtts = new FormAttribute(aAtts) {
			@Override
			public void valueDeleted(int selectedIndex) {
				// TODO Auto-generated method stub
				lbSortOrder.removeItem(selectedIndex);
			}
		};
		faSortAtts.addValueChangeHandler(new ValueChangeHandler<List<String>>() {
			@Override
			public void onValueChange(ValueChangeEvent<List<String>> event) {
				// TODO Auto-generated method stub
				if (pqp.parametrizedQuery.orderBys.size() > lbSortOrder.getItemCount()) {
					lbSortOrder.addItem("A");
				}
				pqp.parametrizedQuery.orderBys = faSortAtts.getValues(true); // event.getValue();
				pqp.parametrizedQuery.orderByDirections = getVauesOfListBox(lbSortOrder);
				orderByDirections = getVauesOfListBox(lbSortOrder);
			}
		});
		faSortAtts.setValue(pqp.parametrizedQuery.orderBys);
		hp.add(faSortAtts);
		faSortAtts.setHeight("100%");
		hp.setCellVerticalAlignment(faSortAtts, HasVerticalAlignment.ALIGN_TOP);

		lbSortOrder = new ListBox();
		lbSortOrder.setWidth("3em");
		lbSortOrder.setVisibleItemCount(faSortAtts.values.getVisibleItemCount());
		lbSortOrder.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent arg0) {
				if (lbSortOrder.getSelectedIndex() > -1) {
					if (lbSortOrder.getSelectedItemText().equals("A"))
						lbSortOrder.setItemText(lbSortOrder.getSelectedIndex(), "D");
					else
						lbSortOrder.setItemText(lbSortOrder.getSelectedIndex(), "A");

					pqp.parametrizedQuery.orderBys = faSortAtts.getValues(true); // event.getValue();
					pqp.parametrizedQuery.orderByDirections = getVauesOfListBox(lbSortOrder);
					orderByDirections = getVauesOfListBox(lbSortOrder);
				}
			}
		});

		lbSortOrder.addChangeHandler(new ChangeHandler() {
			@Override
			public void onChange(ChangeEvent arg0) {
				// TODO Auto-generated method stub
				orderByDirections.clear();
				for (int i = 0; i < lbSortOrder.getItemCount(); i++) {
					orderByDirections.add(lbSortOrder.getItemText(i));
				}
			}
		});

		for (String sortDirection : pqp.parametrizedQuery.orderByDirections) {
			orderByDirections.add(sortDirection);
			lbSortOrder.addItem(sortDirection);
		}

		getContentPanel().add(datagrid);
		getContentPanel().add(new Label("Parameter labels"));
		SimplePanel sp1 = new SimplePanel();
		sp1.setHeight("2em");
		getContentPanel().add(sp1);

		VerticalPanel vp2 = new VerticalPanel();
		SimplePanel sp = new SimplePanel();
		sp.setHeight("2em");
		vp2.add(sp);
		vp2.add(lbSortOrder);

		hp.add(vp2);

		try {
			adminService.getDocTypeFromDql(MainPanel.getInstance().loginName, MainPanel.getInstance().loginPass, dql.getValue(),
					new AsyncCallback<DocType>() {
						@Override
						public void onFailure(Throwable caught) {
							MainPanel.log(caught.getMessage());
						}

						@Override
						public void onSuccess(DocType result) {
							String attsStr = "";
							for (String attKey : result.attributes.keySet()) {
								DcmtAttribute att = result.attributes.get(attKey);
								attsStr = attsStr + att.attr_name + ", ";
							}
							if (attsStr.length() > 0)
								attsStr = attsStr.substring(0, attsStr.length() - 2);
							aAtts.commaSeparatedValueListDefinition = attsStr;
						}
					});
		} catch (ServerException e1) {
			MainPanel.log(e1.getMessage());
		}
		getContentPanel().add(hp);

		Attribute aGroupUser = new Attribute();
		aGroupUser.isRepeating = true;
		aGroupUser.label = "Grupe in uporabniki";
		aGroupUser.dqlValueListDefinition = "select user_name, description from dm_user where 1=1 " + "union "
				+ "select group_name, description from dm_group where 1=1 " + "fixedValues(dm_world, vsi;dm_owner, lastnik; dm_group, skupina)";
		aGroupUser.setType(Attribute.types.DROPDOWN.type);
		aGroupUser.dropDownCol = 0;
		aGroupUser.isLimitedToValueList = true;
		if (!MainPanel.getInstance().loginRole.toLowerCase().equals("administrator")) {
			aGroupUser.isReadOnly = true;
		}
		faGroups = new FormAttribute(aGroupUser);
		MainPanel.log("UsersGroups number: " + this.pqp.parametrizedQuery.usersGroups.size());
		for (UserGroup ug : this.pqp.parametrizedQuery.usersGroups) {
			faGroups.values.addItem(ug.getId(), ug.getParameter());
		}
		getContentPanel().add(faGroups);

		duplicateSearch = new Button("Duplicate search");
		duplicateSearch.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				// TODO Auto-generated method stub
				try {
					adminService.duplicateParametrizedQuery(MainPanel.getInstance().loginName, MainPanel.getInstance().loginPass,
							ParametrizedQueryPanel.lastParametrizedQuery.name, SearchEdit.this.name.getValue(), SearchEdit.this.dql.getValue(),
							new AsyncCallback<String>() {
								@Override
								public void onSuccess(String result) {
									MenuPanel.getInstance().createSearchItems();
									MainPanel.log("Search duplicated under name: " + result);
								}

								@Override
								public void onFailure(Throwable caught) {
									MainPanel.log("Error duplicating search: " + caught.getMessage());
								}
							});
				} catch (ServerException e) {
					MainPanel.log("Error duplicating search: " + e.getMessage());
				}
			}
		});
		getButtonPanel().add(duplicateSearch);
		if (!MainPanel.getInstance().loginRole.toLowerCase().equals("administrator")) {
			duplicateSearch.setEnabled(false);
		}

		deleteSearch = new Button("Delete search");
		deleteSearch.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				// TODO Auto-generated method stub
				try {
					adminService.deleteParametrizedQuery(MainPanel.getInstance().loginName, MainPanel.getInstance().loginPass,
							ParametrizedQueryPanel.lastParametrizedQuery.name, new AsyncCallback<String>() {
								@Override
								public void onSuccess(String result) {
									MenuPanel.getInstance().createSearchItems();
									MainPanel.log("Deleted search.");
								}

								@Override
								public void onFailure(Throwable caught) {
									MainPanel.log("Error delete search.");
								}
							});
				} catch (ServerException e) {
					MainPanel.log("Error deleting search: " + e.getMessage());
				}
			}
		});
		getButtonPanel().add(deleteSearch);
		if (!MainPanel.getInstance().loginRole.toLowerCase().equals("administrator")) {
			deleteSearch.setEnabled(false);
		}

		getOkButton().addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				try {

					List<String> pars = new ArrayList<String>();
					Iterator<ParameterLabel> it = SearchEdit.this.datagrid.getVisibleItems().listIterator();
					while (it.hasNext()) {
						ParameterLabel parLab = it.next();
						pars.add(parLab.label);
						MainPanel.log("added: " + parLab.label);
					}

					adminService.editParametrizedQuery(MainPanel.getInstance().loginName, MainPanel.getInstance().loginPass, oldName,
							name.getTextBox().getValue(), dql.getTextBox().getValue(), faGroups.getValues(true), faSortAtts.getValues(true), orderByDirections,
							filterClass.getValue(), pars, new AsyncCallback<Void>() {
								@Override
								public void onSuccess(Void result) {
									MainPanel.log("Saved search: <strong>" + name.getValue() + "</strong>");
									try {
										adminService.getParametrizedQueryByName(MainPanel.getInstance().loginName, MainPanel.getInstance().loginPass, name.getValue(),
												new AsyncCallback<MyParametrizedQuery>() {
													public void onSuccess(MyParametrizedQuery result) {
														MainPanel.log("Retrieved search: <strong>" + name.getValue() + "</strong>");
														SearchEdit.this.pqp.refresh(result);
													};

													@Override
													public void onFailure(Throwable caught) {
														MainPanel.log(caught.getMessage());
													}

												});
									} catch (ServerException e) {
										MainPanel.log(e.getMessage());
									}
									SearchEdit.this.hide();
								}

								@Override
								public void onFailure(Throwable caught) {
									MainPanel.log(caught.getMessage());
								}
							});
				} catch (ServerException e) {
					MainPanel.log(e.getMessage());
				}
			};

		});

		Scheduler.get().scheduleDeferred(new ScheduledCommand() {
			@Override
			public void execute() {
				SearchEdit.this.center();
			}
		});

	}

	private void parseDqlAndGetLabels() {
		// Compile and use regular expression
		ArrayList<String> pars = new ArrayList<String>();
		Iterator<ParameterLabel> it = SearchEdit.this.datagrid.getVisibleItems().listIterator();
		while (it.hasNext()) {
			ParameterLabel parLab = it.next();
			pars.add(parLab.label);
		}

		
		RegExp regExp = RegExp.compile("(?:UPPER\\(|LOWER\\()*(\\w+)?(?:\\)*?)(?=[ ]*([<>=]|LIKE)).*?([<]\\d*[>]+)", "gi");
		String input = dql.getTextBox().getText();
		if (input != null) {
			ArrayList<ParameterLabel> al = new ArrayList<ParameterLabel>();
			MatchResult matcher = regExp.exec(input);
			int i=0;
			while (matcher != null) {
				if(i<pars.size())
					al.add(new ParameterLabel(matcher.getGroup(3), pars.get(i)));
				else
					al.add(new ParameterLabel(matcher.getGroup(3), matcher.getGroup(1)));
				matcher = regExp.exec(input);
				i++;
			}
			datagrid.setRowData(0, al);
			datagrid.setVisibleRange(0, al.size());
		}
	}

	private ArrayList<String> getVauesOfListBox(ListBox lb) {
		ArrayList<String> ret = new ArrayList<String>();
		for (int i = 0; i < lb.getItemCount(); i++) {
			ret.add(lb.getItemText(i));
		}
		return ret;
	}

	private class ParameterLabel {
		public String parameter;
		public String label;

		public ParameterLabel(String par, String lab) {
			this.parameter = par;
			this.label = lab;
		}
	}

}
