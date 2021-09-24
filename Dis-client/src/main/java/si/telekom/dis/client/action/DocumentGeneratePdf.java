package si.telekom.dis.client.action;

import java.util.List;
import java.util.logging.Logger;

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.Scheduler;
import com.google.gwt.core.client.Scheduler.ScheduledCommand;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.safehtml.shared.SafeUri;
import com.google.gwt.safehtml.shared.UriUtils;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Frame;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.Widget;

import si.telekom.dis.client.MainPanel;
import si.telekom.dis.client.MyListBox;
import si.telekom.dis.client.MyTxtBox;
import si.telekom.dis.client.WindowBox;
import si.telekom.dis.client.item.FormAttribute;
import si.telekom.dis.shared.Attribute;

public class DocumentGeneratePdf extends WindowBox {
	private final static Logger logger = java.util.logging.Logger.getLogger("mylogger");

	Frame html;
	ScrollPanel sp;
	MyTxtBox txtBoxInetId;
	FormAttribute fa;
	FormAttribute faValues;
	Attribute values;

	MyListBox listBox;

	public DocumentGeneratePdf() {
		setText("ERender generate PDF");
		setGlassEnabled(true);

		html = new Frame();
		html.setWidth(getMaxWidthPx());
		html.setHeight("800px");

		sp = new ScrollPanel();
		sp.setHeight(getMaxHeightPx());
		sp.add(html);

		txtBoxInetId = new MyTxtBox("inet_id eObrazca");
		// txtBoxInetId.getTextBox().addChangeHandler(new ChangeHandler() {
		// @Override
		// public void onChange(ChangeEvent event) {
		// refreshInetId();
		// }
		// });
		txtBoxInetId.getTextBox().addKeyUpHandler(new KeyUpHandler() {

			@Override
			public void onKeyUp(KeyUpEvent event) {
				if (event.getNativeKeyCode() == KeyCodes.KEY_ENTER) {
					refreshInetId();
				}
			}
		});

		getContentPanel().add(txtBoxInetId);

		MyListBox lVersion = new MyListBox("version");
		lVersion.addItem("draft");
		lVersion.addItem("sb1");
		lVersion.addItem("sb2");
		lVersion.addItem("effective");
		lVersion.setSelectedIndex(0);
		getContentPanel().add(lVersion);

		Attribute a = new Attribute();
		a.setName("mob_template_id");
		a.dqlValueListDefinition = "select mob_template_id, title, a_content_type from mob_form_template where (a_content_type='odt' or a_content_type='html' or a_content_type='crtext') and any r_version_label = '"
				+ lVersion.getValue() + "' order by title";
		a.label = "Predloga dokumenta";
		a.setType(Attribute.types.DROPDOWN.type);
		a.isLimitedToValueList = true;
		fa = new FormAttribute(a);
		fa.setWidth("100%");
		getContentPanel().add(fa);

		values = new Attribute();
		values.setName("values");
		values.isRepeating = true;
		values.setType(Attribute.types.DROPDOWN.type);
		faValues = new FormAttribute(values);
		faValues.setTitle("polja za predlogo");
		faValues.setWidth("100%");
		getContentPanel().add(faValues);

		lVersion.addChangeHandler(new ChangeHandler() {
			@Override
			public void onChange(ChangeEvent event) {
				a.dqlValueListDefinition = "select mob_template_id, title, a_content_type from mob_form_template where (a_content_type='odt' or a_content_type='html' or a_content_type='crtext') and any r_version_label = '"
						+ lVersion.getValue() + "' order by title";
				// refreshTypeId();
			}
		});

		fa.addValueChangeHandler(new ValueChangeHandler<List<String>>() {

			@Override
			public void onValueChange(ValueChangeEvent<List<String>> event) {
				try {
					refreshTypeId();
				} catch (Exception ex) {
					MainPanel.log("error: " + ex.getMessage());
				}

				String typeIdstr = fa.getValue().split("\\|")[0];
				Integer typeIdInt = Integer.valueOf(typeIdstr);
				
				explorerService.erenderTemplateFields(typeIdInt, new AsyncCallback<List<String>>() {

					@Override
					public void onSuccess(List<String> result) {
						values.commaSeparatedValueListDefinition = "";
						for (String field : result) {
							values.commaSeparatedValueListDefinition = values.commaSeparatedValueListDefinition + field + ",";
						}
						if (values.commaSeparatedValueListDefinition.length() > 0)
							values.commaSeparatedValueListDefinition = values.commaSeparatedValueListDefinition.substring(1,
									values.commaSeparatedValueListDefinition.length());
					}

					@Override
					public void onFailure(Throwable caught) {
						MainPanel.log("error: " + caught.getMessage());
					}
				});

			}
		});
		// fa.addKeyUpHandler(new KeyUpHandler() {
		//
		// @Override
		// public void onKeyUp(KeyUpEvent event) {
		// if (event.getNativeKeyCode() == KeyCodes.KEY_ENTER)
		// refreshTypeId();
		// }
		// });

		getContentPanel().add(sp);

		getOkButton().addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent arg0) {
				// TODO Auto-generated method stub
				DocumentGeneratePdf.this.hide();
			}
		});

		Scheduler.get().scheduleDeferred(new ScheduledCommand() {
			@Override
			public void execute() {
				DocumentGeneratePdf.this.center();
			}
		});
	}

	protected void refreshTypeId() {
		SafeUri safeUri = DocumentGeneratePdf.this.calculateSafeUriGetContent(fa.getValue().split("\\|")[0]);
		html.setUrl(safeUri);
		//hidePrint();
	}

	protected void refreshInetId() {
		SafeUri uri = calculateSafeUriGetPdf(txtBoxInetId.getValue());
		html.setUrl(uri);
		//hidePrint();
	}

	public SafeUri calculateSafeUriGetPdf(String id) {
		return UriUtils.fromString(GWT.getHostPageBaseURL() + "WebUi2/eRenderServlet?loginName=" + MainPanel.getInstance().loginName + "&loginPassword="
				+ MainPanel.getInstance().loginPass + "&inetId=" + id + "&format=pdf");
	}

	public SafeUri calculateSafeUriGetContent(String id) {
		String format = "pdf";
		if (fa.getValue().endsWith("html"))
			format = "html";
		else if (fa.getValue().endsWith("html"))
			format = "crtext";

		return UriUtils.fromString(GWT.getHostPageBaseURL() + "WebUi2/eRenderServlet?loginName=" + MainPanel.getInstance().loginName + "&loginPassword="
				+ MainPanel.getInstance().loginPass + "&typeId=" + id + "&format=" + format);
	}

	public static Widget getMenuItem() {
		// TODO Auto-generated method stub
		Button b = new Button("ERender PDF/Html");
		b.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				MainPanel.clearPanel();
				MainPanel.getPanel().add(new DocumentGeneratePdf());
			}
		});
		return b;
	}

	private native void hidePrint()
	/*-{
		var $printSearch = setInterval(function() {
			if ($wnd.$("button[id='print']").length > 0
					|| $wnd.$("button[id='print']").is(':visible')) {
				hidePrint();
			} else {
				//doNothing
				console.log('Searching...');
			}
		}, 150);

		function hidePrint() {
			$wnd.$("button[id='print']").css('display', 'none');
		}

	}-*/;

}
