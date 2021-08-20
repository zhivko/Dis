package si.telekom.dis.client.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Logger;

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.Scheduler;
import com.google.gwt.core.client.Scheduler.ScheduledCommand;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.safehtml.shared.SafeUri;
import com.google.gwt.safehtml.shared.UriUtils;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.SerializationException;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.CheckBox;

import si.telekom.dis.client.ExplorerPanel;
import si.telekom.dis.client.MainPanel;
import si.telekom.dis.client.MenuPanel;
import si.telekom.dis.client.MyTxtBox;
import si.telekom.dis.client.WindowBox;
import si.telekom.dis.shared.ExplorerService;
import si.telekom.dis.shared.ExplorerServiceAsync;

public class DecryptZipFile extends WindowBox {
	String r_object_id;
	private final static ExplorerServiceAsync explorerService = GWT.create(ExplorerService.class);
	private final static Logger logger = java.util.logging.Logger.getLogger("mylogger");

	MyTxtBox documentumPathToPK;

	public DecryptZipFile(String r_object_id_) {
		r_object_id = r_object_id_;
		setText("Document Delete");
		setGlassEnabled(true);

		documentumPathToPK = new MyTxtBox("Documentum path to privateKeyToDecrypt:");
		documentumPathToPK.setValue("/Certificates/VALU_VideoID_Storage_test.pfx");
		getContentPanel().add(documentumPathToPK);

		getOkButton().addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				// TODO Auto-generated method stub
				explorerService.decryptZip(MainPanel.getInstance().loginName, MainPanel.getInstance().loginPass, r_object_id, documentumPathToPK.getValue(),
						new AsyncCallback<List<String>>() {
							@Override
							public void onSuccess(List<String> result) {
								MainPanel.log("Prepared decrypted ZIP succesfull: " + result.size() + " files.");
								for (String file : result) {
									Anchor a = new Anchor(file);
									try {
										a.setHref(calculateSafeUriDownload(file));
										getContentPanel().add(a);
									} catch (Exception e) {
										MainPanel.log("Error: " + e.getMessage());
									}
								}
							}

							@Override
							public void onFailure(Throwable caught) {
								MainPanel.log("Error deleting object: " + caught.getMessage());
							}
						});
			}
		});

		Scheduler.get().scheduleDeferred(new ScheduledCommand() {
			@Override
			public void execute() {
				DecryptZipFile.this.center();
			}
		});

	}

	public SafeUri calculateSafeUriDownload(String fileName) throws IOException, SerializationException {
//@formatter:off				
		SafeUri safeUriUploadDoc = UriUtils.fromString(GWT.getHostPageBaseURL() + "WebUi2/explorer?"
				+ "loginName=" + MainPanel.getInstance().loginName
				+ "&loginPassword=" + MainPanel.getInstance().loginPass
				+ "&decryptedZipFile=" + fileName);
//@formatter:on			
		return safeUriUploadDoc;
	}
}
