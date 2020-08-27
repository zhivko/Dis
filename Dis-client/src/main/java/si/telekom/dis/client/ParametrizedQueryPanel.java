package si.telekom.dis.client;

import java.util.logging.Logger;

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.Scheduler;
import com.google.gwt.core.client.Scheduler.ScheduledCommand;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.ToggleButton;
import com.google.gwt.user.client.ui.VerticalPanel;

import si.telekom.dis.client.action.SearchEdit;
import si.telekom.dis.client.item.FormAttribute;
import si.telekom.dis.shared.Attribute;
import si.telekom.dis.shared.ExplorerService;
import si.telekom.dis.shared.ExplorerServiceAsync;
import si.telekom.dis.shared.MyParametrizedQuery;
import si.telekom.dis.shared.ServerException;

public class ParametrizedQueryPanel extends WindowBox {
	String r_object_id;
	private final static ExplorerServiceAsync explorerService = GWT.create(ExplorerService.class);
	private final static Logger logger = Logger.getLogger("mylogger");
	public static MyParametrizedQuery lastParametrizedQuery;

	public MyParametrizedQuery parametrizedQuery;

	public ParametrizedQueryPanel(MyParametrizedQuery parametrizedQuery) {
		super();
		this.parametrizedQuery = parametrizedQuery;

		setText("Search - " + parametrizedQuery.name);
		setGlassEnabled(true);

		VerticalPanel vp = new VerticalPanel();
		ScrollPanel sp = new ScrollPanel(vp);
		refresh(parametrizedQuery);

		getOkButton().addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				runQuery();
			}
		});

		getContentPanel().add(sp);

	}

	protected void runQuery() {
		String dql = prepareDql();
		// dql = dql.replaceAll("\\$version", "CURRENT");
		lastParametrizedQuery = this.parametrizedQuery;
		SearchPanel.getSearchPanelInstance().clearSelectedSet();
		SearchPanel.getSearchPanelInstance().runReadDqlQuery(dql);
		ParametrizedQueryPanel.this.hide();
	}

	protected String prepareDql() {
		int i = 0;

		String dql = parametrizedQuery.dql;
		MainPanel.log(dql);
		for (String argument : parametrizedQuery.arguments) {
			FormAttribute a = (FormAttribute) getContentPanel().getWidget(i);
			if (a.att.type.equals("datetime")) {
				dql = dql.replaceAll(parametrizedQuery.arguments.get(i), a.getDateTime());
			} else
				dql = dql.replaceAll(parametrizedQuery.arguments.get(i), a.getValue());

			dql = dql.replaceAll(parametrizedQuery.labels.get(i), "");
			i++;
		}
		return dql;
	}

	public HorizontalPanel getButtonPanel() {
		HorizontalPanel hp = new HorizontalPanel();

		ToggleButton b = new ToggleButton(this.parametrizedQuery.name);
		b.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				
				try {
					getAdminService().getParametrizedQueryByName(MainPanel.getInstance().loginName, MainPanel.getInstance().loginPass, parametrizedQuery.name, new AsyncCallback<MyParametrizedQuery>() {
						
						@Override
						public void onSuccess(MyParametrizedQuery result) {
							ParametrizedQueryPanel.this.refresh(result);
							
							if (parametrizedQuery.formAttributes.size() == 0) {
								lastParametrizedQuery = ParametrizedQueryPanel.this.parametrizedQuery;
								SearchPanel.getSearchPanelInstance().runReadDqlQuery(prepareDql());
								ParametrizedQueryPanel.this.hide();
							} else {
								ParametrizedQueryPanel.this.show();
								Scheduler.get().scheduleDeferred(new ScheduledCommand() {
									@Override
									public void execute() {
										ParametrizedQueryPanel.this.center();
										
										if (ParametrizedQueryPanel.this.getContentPanel().getWidgetCount() > 0) {
											((FormAttribute) (ParametrizedQueryPanel.this.getContentPanel().getWidget(0))).setFocus();
										}
										
									}
								});
							}
							MenuPanel.getInstance().resetSearchButtons();
							b.setDown(true);
							
							
						}
						
						@Override
						public void onFailure(Throwable caught) {
							MainPanel.log(caught.getMessage());
						}
					});
				} catch (ServerException e) {
					MainPanel.log(e.getMessage());
				}
				
			}
		});
		hp.add(b);

		Anchor a = new Anchor();
		a.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				SearchEdit se = new SearchEdit(ParametrizedQueryPanel.this);
				se.show();
			}
		});
		a.setText("...");
		hp.add(a);

		return hp;
	}

	public void refresh(MyParametrizedQuery result) {
		// TODO Auto-generated method stub
		this.parametrizedQuery = result;
		lastParametrizedQuery = result;

		getContentPanel().clear();

		int i = 0;
		for (String partOfDql : parametrizedQuery.dqlParts) {
			Attribute a = parametrizedQuery.formAttributes.get(i);
			FormAttribute fa = new FormAttribute(a);
			fa.addKeyUpHandler(new KeyUpHandler() {

				@Override
				public void onKeyUp(KeyUpEvent event) {
					if (event.getNativeKeyCode() == KeyCodes.KEY_ENTER) {
						runQuery();
					}
				}
			});
			getContentPanel().add(fa);
			i++;
		}

	}

}
