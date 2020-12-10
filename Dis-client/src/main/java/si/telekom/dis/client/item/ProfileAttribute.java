package si.telekom.dis.client.item;

import java.util.logging.Logger;

import com.google.gwt.dom.builder.shared.OptionBuilder;
import com.google.gwt.event.dom.client.BlurEvent;
import com.google.gwt.event.dom.client.BlurHandler;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.FocusEvent;
import com.google.gwt.event.dom.client.FocusHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.RadioButton;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

import si.telekom.dis.client.MainPanel;
import si.telekom.dis.client.MyTextArea;
import si.telekom.dis.client.MyTxtBox;
import si.telekom.dis.client.action.NewProfile;
import si.telekom.dis.shared.Attribute;

public class ProfileAttribute extends IsSelected {
	private final String styleName = "profileAttribute";

	static Logger logger = java.util.logging.Logger.getLogger("mylogger");

	private MyTxtBox labelTextBox;
	private Label labelAttName;
	private boolean isSelected = false;
	private VerticalPanel vp;
	private ListBox typeLb;

	private CheckBox isRepeating;
	private CheckBox isReadOnly;
	private CheckBox isMandatory;
	private CheckBox isLimitedToValueList;

	private MyTextArea defaultValueTextArea;
	
	private RadioButton rbDefaultValueIsConstant = new RadioButton("defaultValueType");
	private RadioButton rbDefaultValueIsDql= new RadioButton("defaultValueType");
	private RadioButton rbDefaultValueIsSql = new RadioButton("defaultValueType");
	private CheckBox cbDefaultValueIsCalculatedOnServer;
	
	private MyTextArea jdbcValueListDefinition;
	private MyTextArea dqlValueListDefinition;
	private MyTxtBox commaSeparatedValueListDefinition;
	private MyTextArea restQuery;
	private MyTxtBox dependsOn;
	private MyTxtBox dropDownCol;

	public Attribute attr;

	public ProfileAttribute() {
		vp = new VerticalPanel();
		PopupPanel pop = new PopupPanel();
		pop.setModal(true);
		pop.setAutoHideEnabled(true);

		Label lblMoveUp = new Label();
		lblMoveUp.setStyleName("popUpItem");
		lblMoveUp.setText("Gor");
		lblMoveUp.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				logger.info("Gor. Parent is of class: " + ProfileAttribute.this.getParent().getClass().getName());
				if (ProfileAttribute.this.getParent().getClass().getName().contains("VerticalContainer")) {
					// parentVertCont.removeItem(parentVertCont.getSelectedIndex());
				}
				pop.hide(true);
			}
		});
		Label lblDelete = new Label();
		lblDelete.setStyleName("popUpItem");
		lblDelete.setText("Briši");
		lblDelete.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				logger.info("Briši. Parent is of class: " + ProfileAttribute.this.getParent().getClass().getName());
				if (ProfileAttribute.this.getParent().getClass().getName().contains("VerticalContainer")) {

				}
				pop.hide(true);
			}
		});

		VerticalPanel vp1 = new VerticalPanel();
		vp1.add(lblMoveUp);
		vp1.add(lblDelete);

		pop.add(vp1);

		labelAttName = new Label();

		labelTextBox = new MyTxtBox("labela") {
			public void valueChanged() {
				ProfileAttribute.this.attr.label = labelTextBox.getValue();
			};
		};
		labelTextBox.setStyleName(styleName);

		isReadOnly = new CheckBox();
		isReadOnly.setText("Samo za branje");
		isReadOnly.addValueChangeHandler(new ValueChangeHandler<Boolean>() {
			@Override
			public void onValueChange(ValueChangeEvent<Boolean> event) {
				// TODO Auto-generated method stub
				ProfileAttribute.this.attr.isReadOnly = isReadOnly.getValue();
			}
		});

		isRepeating = new CheckBox();
		isRepeating.setText("več vrednosti");
		isRepeating.addValueChangeHandler(new ValueChangeHandler<Boolean>() {
			@Override
			public void onValueChange(ValueChangeEvent<Boolean> event) {
				// TODO Auto-generated method stub
				ProfileAttribute.this.attr.isRepeating = (isRepeating.getValue());
			}
		});

		isMandatory = new CheckBox();
		isMandatory.setText("obvezen");
		isMandatory.addValueChangeHandler(new ValueChangeHandler<Boolean>() {
			@Override
			public void onValueChange(ValueChangeEvent<Boolean> event) {
				// TODO Auto-generated method stub
				ProfileAttribute.this.attr.isMandatory = (isMandatory.getValue());
			}
		});

		typeLb = new ListBox();
		typeLb.addItem(Attribute.types.TEXTBOX.type);
		typeLb.addItem(Attribute.types.TEXTAREA.type);
		typeLb.addItem(Attribute.types.CHECKBOX.type);
		typeLb.addItem(Attribute.types.DROPDOWN.type);
		typeLb.addItem(Attribute.types.CALENDAR.type);
		typeLb.addItem(Attribute.types.DATETIME.type);
		typeLb.addItem(Attribute.types.DATE.type);
		typeLb.addItem(Attribute.types.TIME.type);
		typeLb.addChangeHandler(new ChangeHandler() {

			@Override
			public void onChange(ChangeEvent event) {
				String type_ = typeLb.getSelectedItemText();
				ProfileAttribute.this.attr.setType(type_);
			}
		});

		HorizontalPanel hp1 = new HorizontalPanel();

		isLimitedToValueList = new CheckBox();
		isLimitedToValueList.setText("omejen");
		isLimitedToValueList.addValueChangeHandler(new ValueChangeHandler<Boolean>() {
			@Override
			public void onValueChange(ValueChangeEvent<Boolean> event) {
				// TODO Auto-generated method stub
				ProfileAttribute.this.attr.isLimitedToValueList = (isLimitedToValueList.getValue());
			}
		});
		hp1.add(isLimitedToValueList);

		cbDefaultValueIsCalculatedOnServer = new CheckBox();
		cbDefaultValueIsCalculatedOnServer.setText("izračunan na serverju");
		cbDefaultValueIsCalculatedOnServer.addValueChangeHandler(new ValueChangeHandler<Boolean>() {
			@Override
			public void onValueChange(ValueChangeEvent<Boolean> event) {
				// TODO Auto-generated method stub
				ProfileAttribute.this.attr.defaultValueIsCalculatedOnServer = cbDefaultValueIsCalculatedOnServer.getValue();
			}
		});
		hp1.add(cbDefaultValueIsCalculatedOnServer);
		
		
		
		dropDownCol = new MyTxtBox("Stolpec za prenos v vrednost att") {
			@Override
			public void valueChanged() {
				attr.dropDownCol = Integer.valueOf(this.getValue());
			}
		};
		hp1.add(dropDownCol);

		VerticalPanel vpType = new VerticalPanel();
		vpType.add(typeLb);
		vpType.add(new Label("tip"));

		HorizontalPanel hp = new HorizontalPanel();
		SimplePanel space = new SimplePanel();
		space.setWidth("10px");
		hp.add(space);

		space = new SimplePanel();
		space.setWidth("10px");
		hp.add(space);

		hp.add(isReadOnly);
		space = new SimplePanel();
		space.setWidth("10px");
		hp.add(space);

		hp.add(isRepeating);
		space = new SimplePanel();
		space.setWidth("10px");
		hp.add(space);

		hp.add(isMandatory);
		space = new SimplePanel();
		space.setWidth("10px");
		hp.add(space);

		hp.add(vpType);

		HorizontalPanel hp0 = new HorizontalPanel();
		// hp0.setBorderWidth(2);
		hp0.setWidth("100%");
		hp0.add(labelTextBox);
		labelTextBox.setStyleName("profileAttribute.attributeLabel");

		hp0.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_RIGHT);
		hp0.add(labelAttName);

		Button insertRow = new Button("insert row");
		insertRow.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				NewProfile.instance.insertRow(ProfileAttribute.this);
			}
		});
		hp0.add(insertRow);
		
		
		Button remove = new Button("remove");
		remove.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				NewProfile.instance.removeProfileAttribute(ProfileAttribute.this);
			}
		});
		hp0.add(remove);

		Button left = new Button("&larr;");
		left.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				NewProfile.instance.leftProfileAttribute(ProfileAttribute.this);
			}
		});
		hp0.add(left);
		Button right = new Button("&rarr;");
		right.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				NewProfile.instance.rightProfileAttribute(ProfileAttribute.this);
			}
		});
		hp0.add(right);
		Button up = new Button("&uarr;");
		up.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				NewProfile.instance.upProfileAttribute(ProfileAttribute.this);
			}
		});
		hp0.add(up);
		Button down = new Button("&darr;");
		down.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				NewProfile.instance.downProfileAttribute(ProfileAttribute.this);
			}
		});
		hp0.add(down);

		vp.add(hp0);
		vp.add(hp);

		space = new SimplePanel();
		space.setHeight("10px");
		vp.add(space);

		defaultValueTextArea = new MyTextArea("privzeta vrednost") {
			@Override
			public void valueChanged() {
				attr.defaultValue = this.getValue();
			}
		};
		defaultValueTextArea.setStyleName(styleName);
		HorizontalPanel hpDefaultValue = new HorizontalPanel();
		hpDefaultValue.add(defaultValueTextArea);

		rbDefaultValueIsConstant = new RadioButton("defaultValueType");
		rbDefaultValueIsConstant.setText("constant");
		rbDefaultValueIsDql= new RadioButton("defaultValueType");
		rbDefaultValueIsDql.setText("dql");
		rbDefaultValueIsSql = new RadioButton("defaultValueType");
		rbDefaultValueIsSql.setText("sql");
		rbDefaultValueIsConstant.addValueChangeHandler(new ValueChangeHandler<Boolean>() {
			@Override
			public void onValueChange(ValueChangeEvent<Boolean> event) {
				if (event.getValue()) {
					attr.defaultValueIsConstant = true;
					attr.defaultValueIsDql = false;
					attr.defaultValueIsSql = false;
				}
				MainPanel.log(attr.defaultValueIsConstant + " " + attr.defaultValueIsDql + " " + attr.defaultValueIsSql);
			}
		});
		rbDefaultValueIsDql.addValueChangeHandler(new ValueChangeHandler<Boolean>() {
			@Override
			public void onValueChange(ValueChangeEvent<Boolean> event) {
				if (event.getValue()) {
					attr.defaultValueIsConstant = false;
					attr.defaultValueIsDql = true;
					attr.defaultValueIsSql = false;
				}
				MainPanel.log(attr.defaultValueIsConstant + " " + attr.defaultValueIsDql + " " + attr.defaultValueIsSql);
			}
		});
		rbDefaultValueIsSql.addValueChangeHandler(new ValueChangeHandler<Boolean>() {
			@Override
			public void onValueChange(ValueChangeEvent<Boolean> event) {
				if (event.getValue()) {
					attr.defaultValueIsConstant = false;
					attr.defaultValueIsDql = false;
					attr.defaultValueIsSql = true;
				}
				MainPanel.log(attr.defaultValueIsConstant + " " + attr.defaultValueIsDql + " " + attr.defaultValueIsSql);
			}
		});

		hpDefaultValue.add(rbDefaultValueIsConstant);
		hpDefaultValue.add(rbDefaultValueIsDql);
		hpDefaultValue.add(rbDefaultValueIsSql);

		vp.add(hpDefaultValue);

		HTML htmlHorLine = new HTML("<hr  style=\"width:80%;\" />");

		space = new SimplePanel();
		space.setHeight("10px");
		vp.add(space);
		vp.add(space);
		vp.add(htmlHorLine);

		vp.add(hp1);
		space = new SimplePanel();
		space.setWidth("10px");
		vp.add(space);

		space = new SimplePanel();
		space.setWidth("10px");
		vp.add(space);

		jdbcValueListDefinition = new MyTextArea("odbc sql nabor vrednosti", 300) {
			@Override
			public void valueChanged() {
				attr.jdbcValueListDefinition = this.getValue();
			}
		};

		jdbcValueListDefinition.setStyleName(styleName);
		vp.add(jdbcValueListDefinition);
		space = new SimplePanel();
		space.setWidth("10px");
		vp.add(space);

		dqlValueListDefinition = new MyTextArea("dql nabor vrednosti", 300) {
			@Override
			public void valueChanged() {
				attr.dqlValueListDefinition = this.getValue();
			}
		};
		dqlValueListDefinition.setStyleName(styleName);
		vp.add(dqlValueListDefinition);
		space = new SimplePanel();
		space.setWidth("10px");
		vp.add(space);

		commaSeparatedValueListDefinition = new MyTxtBox("vrednosti ločene z vejico", 300) {
			@Override
			public void valueChanged() {
				attr.commaSeparatedValueListDefinition = this.getValue();
			}
		};
		commaSeparatedValueListDefinition.setStyleName(styleName);
		vp.add(commaSeparatedValueListDefinition);

		restQuery = new MyTextArea("restQuery", 300) {
			@Override
			public void valueChanged() {
				attr.restQuery = this.getValue();
			}
		};
		restQuery.setStyleName(styleName);
		vp.add(restQuery);

		dependsOn = new MyTxtBox("dependsOn", 300) {
			@Override
			public void valueChanged() {
				attr.dependsOn = this.getValue();
			}
		};
		dependsOn.setStyleName(styleName);
		vp.add(dependsOn);

		initWidget(vp);
	}

	public ProfileAttribute(Attribute a) {
		this();
		this.attr = a;

		labelTextBox.setValue(a.label);
		this.labelAttName.setText(a.dcmtAttName);
		this.labelAttName.setStyleName("font-weight: bold;");

		for (int i = 0; i < this.typeLb.getItemCount(); i++) {
			if (this.typeLb.getItemText(i).equals(attr.getType())) {
				this.typeLb.setSelectedIndex(i);
				break;
			}
		}

		this.isRepeating.setValue(this.attr.isRepeating);
		this.isReadOnly.setValue(this.attr.isReadOnly);
		this.isMandatory.setValue(attr.isMandatory);
		this.isLimitedToValueList.setValue(attr.isLimitedToValueList);
		this.cbDefaultValueIsCalculatedOnServer.setValue(this.attr.defaultValueIsCalculatedOnServer);

		if (attr.commaSeparatedValueListDefinition != null)
			if (!attr.commaSeparatedValueListDefinition.equals("null"))
				this.commaSeparatedValueListDefinition.setValue(attr.commaSeparatedValueListDefinition);
		if (attr.dqlValueListDefinition != null)
			if (!attr.dqlValueListDefinition.equals("null"))
				this.dqlValueListDefinition.setValue(attr.dqlValueListDefinition);
		if (attr.jdbcValueListDefinition != null)
			if (!attr.jdbcValueListDefinition.equals("null"))
				this.jdbcValueListDefinition.setValue(attr.jdbcValueListDefinition);

		if (attr.restQuery != null)
			restQuery.setValue(attr.restQuery);
		if (attr.dependsOn != null)
			dependsOn.setValue(attr.dependsOn);

		dropDownCol.setValue(String.valueOf(attr.dropDownCol));
		
		defaultValueTextArea.setValue(attr.defaultValue);
		rbDefaultValueIsConstant.setValue(attr.defaultValueIsConstant);
		rbDefaultValueIsDql.setValue(attr.defaultValueIsDql);
		rbDefaultValueIsSql.setValue(attr.defaultValueIsSql);

		vp.getElement().setId("profileAttribute:" + attr.tabId + "~" + attr.tabId + "~" + attr.dcmtAttName + "~" + attr.row + "~" + attr.col);
	}

	protected void setIsSelected(boolean b) {
		this.isSelected = b;
		if (this.isSelected)
			vp.getElement().getStyle().setBackgroundColor("rgba(51, 142, 183, 0.39)");
		else
			vp.getElement().getStyle().setBackgroundColor("");
	}

	public String getDctmAttName() {
		return attr.dcmtAttName;
	}

	@Override
	public Widget asWidget() {
		// TODO Auto-generated method stub
		return vp;
	}

	public String getName() {
		return attr.dcmtAttName;
	}

}
