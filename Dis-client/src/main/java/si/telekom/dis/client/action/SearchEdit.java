package si.telekom.dis.client.action;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.Scheduler;
import com.google.gwt.core.client.Scheduler.ScheduledCommand;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
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
		dql.setTextAreaHeight("300px");
		dql.setTextAreaWidth("700px");
		hp.add(dql);
		if (MainPanel.getInstance().loginRole.toLowerCase().equals("administrator")) {
			dql.setIsEditable(true);
			name.setIsEditable(true);
		} else {
			dql.setIsEditable(false);
			name.setIsEditable(false);
		}

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
					adminService.editParametrizedQuery(MainPanel.getInstance().loginName, MainPanel.getInstance().loginPass, oldName,
							name.getTextBox().getValue(), dql.getTextBox().getValue(), faGroups.getValues(true), faSortAtts.getValues(true), orderByDirections,
							filterClass.getValue(), new AsyncCallback<Void>() {
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

	private ArrayList<String> getVauesOfListBox(ListBox lb) {
		ArrayList<String> ret = new ArrayList<String>();
		for (int i = 0; i < lb.getItemCount(); i++) {
			ret.add(lb.getItemText(i));
		}
		return ret;
	}
}
