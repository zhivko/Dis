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
import com.google.gwt.event.dom.client.LoadEvent;
import com.google.gwt.event.dom.client.LoadHandler;
import com.google.gwt.http.client.Request;
import com.google.gwt.http.client.RequestBuilder;
import com.google.gwt.http.client.RequestCallback;
import com.google.gwt.http.client.Response;
import com.google.gwt.http.client.URL;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.safehtml.shared.UriUtils;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Frame;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.ScrollPanel;

import si.telekom.dis.client.MainPanel;
import si.telekom.dis.client.MenuPanel;
import si.telekom.dis.client.WindowBox;
import si.telekom.dis.shared.DocumentViewFileTypes;
import si.telekom.dis.shared.ExplorerService;
import si.telekom.dis.shared.ExplorerServiceAsync;

public class DocumentView extends WindowBox {
	final ListBox renditions;
	Frame frame;
	String r_object_id;
	private final static ExplorerServiceAsync explorerService = GWT.create(ExplorerService.class);
	private final static Logger logger = java.util.logging.Logger.getLogger("mylogger");
	ScrollPanel sp;

	public DocumentView(String r_object_id_) {
		r_object_id = r_object_id_;
		setText("Vsebina dokumenta (barkoda: " + MenuPanel.activeExplorerInstance.selectedDocument.object_name + ")");
		
		setGlassEnabled(true);

		sp = new ScrollPanel();

		renditions = new ListBox();
		renditions.addChangeHandler(new ChangeHandler() {
			@Override
			public void onChange(ChangeEvent event) {
				refresh();
			}
		});

		explorerService.getRenditions(MainPanel.getInstance().loginName, MainPanel.getInstance().loginPass, r_object_id_,
				new AsyncCallback<List<String>>() {
					@Override
					public void onSuccess(List<String> result) {
						// TODO Auto-generated method stub
						for (String rendition : result)
							renditions.addItem(rendition);

						if (renditions.getItemCount() > 0) {
							refresh();
						}
					}

					@Override
					public void onFailure(Throwable caught) {
						logger.info(caught.getMessage());
					}
				});

		HorizontalPanel hp = new HorizontalPanel();
		hp.add(new Label("Rendicija"));
		hp.add(renditions);
		
		Button buttonDownload = new Button("Prenesi");
		buttonDownload.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				String url = getUrl(r_object_id, renditions.getSelectedItemText()) + "&download=true";
				String safeUriDocView = UriUtils.fromString(url).asString();
				Window.open(URL.encode(safeUriDocView), "_self", "status=0,toolbar=0,menubar=0,location=0");
			}
		});
		hp.add(buttonDownload);

		getContentPanel().add(hp);
		getContentPanel().add(sp);

		Scheduler.get().scheduleDeferred(new ScheduledCommand() {
			@Override
			public void execute() {
				DocumentView.this.center();
			}
		});
	}

	private void refresh() {
		String rendition = renditions.getSelectedItemText();
		String safeUriDocView = UriUtils.fromString(getUrl(r_object_id, rendition)).asString();

		List<String> odfFormats = DocumentViewFileTypes.odfFormats;

		if (DocumentViewFileTypes.viewerJSFormats.contains(rendition)) {

			String safeUriDocView2 = GWT.getHostPageBaseURL() + "ViewerJS/index.html?navbar=false&title=" + r_object_id + "#" + safeUriDocView;
			frame = new Frame();
			frame.setWidth(getMaxWidthPx());
			frame.setHeight(getMaxHeightPx());
			Scheduler.get().scheduleDeferred(new ScheduledCommand() {
				@Override
				public void execute() {
					DocumentView.this.center();
				}
			});

			frame.setUrl(safeUriDocView2);
			if (sp.getWidget() != null)
				sp.remove(sp.getWidget());
			sp.add(frame);

			logger.info("submitting form to: " + frame.getUrl());
		} else if (DocumentViewFileTypes.odfFormats.contains(rendition)) {
			frame = new Frame();
			frame.setWidth(getMaxWidthPx());
			frame.setHeight(getMaxHeightPx());
			Scheduler.get().scheduleDeferred(new ScheduledCommand() {
				@Override
				public void execute() {
					DocumentView.this.center();
				}
			});
			frame.setUrl(getWebOdfUrl(r_object_id, rendition));
			if (sp.getWidget() != null)
				sp.remove(sp.getWidget());
			sp.add(frame);
			logger.info("submitting form to: " + frame.getUrl());
		} else if (DocumentViewFileTypes.imageHtmlFormats.contains(rendition)) {
			sp.setWidth(getMaxWidthPx());
			sp.setHeight(getMaxHeightPx());

			Image image = new Image();

			image.addLoadHandler(new LoadHandler() {
				@Override
				public void onLoad(LoadEvent event) {
					if (image.getWidth() > getMaxWidth()) {
						image.setWidth("100%");
					}
					MainPanel.log("Image loaded.");
					DocumentView.this.center();
				}
			});
			image.prefetch(safeUriDocView);
			image.setUrl(safeUriDocView);
			sp.add(image);
			MainPanel.log("Image loading...");
			Scheduler.get().scheduleDeferred(new ScheduledCommand() {
				@Override
				public void execute() {
					DocumentView.this.center();
				}
			});
		} else if (DocumentViewFileTypes.downloadFormats.contains(rendition)) {
			Window.open(URL.encode(safeUriDocView), "_self", "status=0,toolbar=0,menubar=0,location=0");
		} else {
			RequestBuilder builder = new RequestBuilder(RequestBuilder.GET, URL.encode(safeUriDocView));
			try {
				String data = "";
				try {
					builder.sendRequest(data, new RequestCallback() {
						public void onError(Request request, Throwable exception) {
							MainPanel.log("error: " + exception.getMessage());
						}

						public void onResponseReceived(Request request, Response response) {
							if (200 == response.getStatusCode()) {
								// Process the response in response.getText()
								// Window.open(url, "_blank", "");
								sp.setWidth(getMaxWidthPx());
								sp.setHeight(getMaxHeightPx());

								if (sp.getWidget() != null)
									sp.remove(sp.getWidget());
								
								HTML html = new HTML(new SafeHtmlBuilder().appendEscapedLines(response.getText()).toSafeHtml());
								
								//HTML html = new HTML(response.getText());
								sp.add(html);
								DocumentView.this.center();
							} else {
								MainPanel.log("StatusCode=" + response.getStatusCode());
							}
						}
					});
				} catch (com.google.gwt.http.client.RequestException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} catch (Exception e) {
				logger.info(e.getMessage());
				MainPanel.log(e.getMessage());
			}
		}

	}

	public static String getUrl(String r_object_id, String rendition) {
		// TODO Auto-generated method stub
		return GWT.getHostPageBaseURL() + "WebUi2/explorerServ?loginName=" + MainPanel.getInstance().loginName + "&loginPassword="
				+ MainPanel.getInstance().loginPass + "&r_object_id=" + r_object_id + "&rendition=" + rendition;
	}

	public static String getWebOdfUrl(String r_object_id, String rendition) {
		// TODO Auto-generated method stub
		return GWT.getHostPageBaseURL() + "WebUi2/explorerServ?loginName=" + MainPanel.getInstance().loginName + "&loginPassword="
				+ MainPanel.getInstance().loginPass + "&r_object_id=" + r_object_id + "&rendition=" + rendition;
	}

	public static native void showpdf(String url) /*-{
		//
		// The workerSrc property shall be specified.
		//
		pdfjsLib.GlobalWorkerOptions.workerSrc = '../../node_modules/pdfjs-dist/build/pdf.worker.js';
		//
		// Asynchronous download PDF
		//
		var loadingTask = pdfjsLib.getDocument(url);
		loadingTask.promise.then(function(pdf) {
			//
			// Fetch the first page
			//
			pdf.getPage(1).then(function(page) {
				var scale = 1.5;
				var viewport = page.getViewport({
					scale : scale,
				});
				//
				// Prepare canvas using PDF page dimensions
				//
				var canvas = document.getElementById('the-canvas');
				var context = canvas.getContext('2d');
				canvas.height = viewport.height;
				canvas.width = viewport.width;
				//
				// Render PDF page into canvas context
				//
				var renderContext = {
					canvasContext : context,
					viewport : viewport,
				};
				page.render(renderContext);
			});
		});
	}-*/;

}
