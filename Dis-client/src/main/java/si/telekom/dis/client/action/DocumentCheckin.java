package si.telekom.dis.client.action;

import java.util.List;
import java.util.logging.Logger;

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.Scheduler;
import com.google.gwt.core.client.Scheduler.ScheduledCommand;
import com.google.gwt.dom.client.NativeEvent;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.safehtml.shared.SafeUri;
import com.google.gwt.safehtml.shared.UriUtils;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.FileUpload;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.FormPanel;
import com.google.gwt.user.client.ui.FormPanel.SubmitCompleteEvent;
import com.google.gwt.user.client.ui.FormPanel.SubmitCompleteHandler;
import com.google.gwt.user.client.ui.FormPanel.SubmitEvent;
import com.google.gwt.user.client.ui.FormPanel.SubmitHandler;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.Panel;

import si.telekom.dis.client.MainPanel;
import si.telekom.dis.client.MenuPanel;
import si.telekom.dis.client.MyTxtBox;
import si.telekom.dis.client.WindowBox;
import si.telekom.dis.shared.ExplorerService;
import si.telekom.dis.shared.ExplorerServiceAsync;

public class DocumentCheckin extends WindowBox {
	String r_object_id;
	private final static ExplorerServiceAsync explorerService = GWT.create(ExplorerService.class);
	private final static Logger logger = java.util.logging.Logger.getLogger("mylogger");

	FileUpload fileUpload;
	FormPanel formPanel;
	Panel uploadPanel;
	HTML html;
	boolean versionTxtOk = true;
	ListBox possibleFormats;

	public DocumentCheckin(String r_object_id_) {
		r_object_id = r_object_id_;
		setText("Document checkin");
		setGlassEnabled(true);

		formPanel = new FormPanel();

		// formPanel.addSubmitCompleteHandler(new FormPanel.SubmitCompleteHandler()
		// {
		// @Override
		// public void onSubmitComplete(SubmitCompleteEvent event) {
		// MainPanel.log(event.getResults());
		// DocumentCheckin.this.hide();
		// }
		// });
		Label lbl = new Label("Upload document");
		fileUpload = new FileUpload();

		fileUpload.setName("uploadFormElement");

		uploadPanel = new FlowPanel();
		uploadPanel.setStyleName("FileSubmit");
		uploadPanel.add(fileUpload);

		MyTxtBox txtVersion = new MyTxtBox("Dodaj labelo verzije");
		uploadPanel.add(txtVersion);
		txtVersion.setName("labelVersion");
		txtVersion.getTextBox().addKeyUpHandler(new KeyUpHandler() {
			
			@Override
			public void onKeyUp(KeyUpEvent event) {
				versionTxtOk = true;
				txtVersion.getTextBox().getElement().getStyle().setBackgroundColor("");

				if (txtVersion.getValue().length() > 0) {
					versionTxtOk = true;
					if (txtVersion.getValue().contains(",") || txtVersion.getValue().length()>32) {
						versionTxtOk = false;
						txtVersion.getTextBox().getElement().getStyle().setBackgroundColor("red");
					}
				}
				
			}
		});
		
		
		possibleFormats = new ListBox();
		possibleFormats.addChangeHandler(new ChangeHandler() {
			@Override
			public void onChange(ChangeEvent event) {
				formPanel.setAction(calculateSafeUriDownload());				
			}
		});

		getOkButton().addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				// get the filename to be uploaded
				mySubmit();
			}
		});

		formPanel.setEncoding(FormPanel.ENCODING_MULTIPART);
		formPanel.setMethod(FormPanel.METHOD_POST);
		formPanel.setAction(calculateSafeUriDownload());

		this.getContentPanel().add(formPanel);
		this.getContentPanel().add(possibleFormats);

		formPanel.setWidget(uploadPanel);

		formPanel.addSubmitHandler(new SubmitHandler() {

			@Override
			public void onSubmit(SubmitEvent event) {
				MainPanel.log("Submitted");
			}
		});

		formPanel.addSubmitCompleteHandler(new SubmitCompleteHandler() {
			@Override
			public void onSubmitComplete(SubmitCompleteEvent event) {
				MainPanel.log("Submit complete, results: " + event.getResults());

				MenuPanel.activeExplorerInstance.refreshLastSelectedNodeByChronicleId();
				DocumentCheckin.this.hide();
			}
		});

		fileUpload.addChangeHandler(new ChangeHandler() {

			@Override
			public void onChange(ChangeEvent event) {
				MainPanel.log("File selected: " + fileUpload.getFilename());
				findPossibleRenditions(DocumentCheckin.this, event.getNativeEvent());
			}
		});

		Scheduler.get().scheduleDeferred(new ScheduledCommand() {
			@Override
			public void execute() {
				DocumentCheckin.this.center();
			}
		});

	}

	@Override
	public void show() {
		super.show();
		fileUpload.click();
	}

	public void mySubmit() {
		String filename = fileUpload.getFilename();
		if (filename.length() == 0) {
			MainPanel.log("No File Specified!");
		} else {
			MainPanel.log("Submitting: " + filename);
			formPanel.submit();
			// RootPanel.get().remove(DocumentCheckin.this);
			// MainPanel.log("Done.");
		}
	}
	
	public SafeUri calculateSafeUriDownload() {
		
		return UriUtils.fromString(GWT.getHostPageBaseURL() + "WebUi2/uploadServlet?loginName=" + MainPanel.getInstance().loginName + "&loginPassword="
				+ MainPanel.getInstance().loginPass + "&objectId=" + this.r_object_id + "&actionId=" + this.getClass().getName() + "&format=" + possibleFormats.getSelectedItemText());
	}	
	
	public void gotBase64(String base64data) {
		possibleFormats.clear();

		explorerService.recognizeFormat(base64data, new AsyncCallback<List<String>>() {

			@Override
			public void onSuccess(List<String> result) {
				for (String format : result) {
					possibleFormats.addItem(format);
				}
				formPanel.setAction(calculateSafeUriDownload());
			}

			@Override
			public void onFailure(Throwable caught) {
				MainPanel.log(caught.getMessage());
			}
		});

	}

	public native void findPossibleRenditions(DocumentCheckin x, NativeEvent event) /*-{

		var image = event.target.files[0];

		// Check if file is an image
		//if (image.type.match('image.*')) {

		var reader = new FileReader();
		reader.onload = function(e) {
			// Call-back Java method when done
			//imageLoaded(e.target.result);
			reader.result; //holds base64 file content
			//$wnd.gotBase64(e.target.result);
			x.@si.telekom.dis.client.action.DocumentCheckin::gotBase64(Ljava/lang/String;)(e.target.result);
		}

		// Start reading the image
		reader.readAsDataURL(image);
		//}
	}-*/;
	
	
}
