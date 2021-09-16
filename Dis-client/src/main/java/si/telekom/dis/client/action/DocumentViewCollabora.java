// https://github.com/Champs-Libres/wopi-bundle/blob/master/src/Resources/views/Editor/embedded.html.twig

package si.telekom.dis.client.action;

import java.util.List;
import java.util.logging.Logger;

import org.gwtbootstrap3.client.ui.html.Div;

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
import com.google.gwt.safehtml.shared.UriUtils;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.FormPanel;
import com.google.gwt.user.client.ui.Frame;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Hidden;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.TextBox;

import si.telekom.dis.client.MainPanel;
import si.telekom.dis.client.WindowBox;
import si.telekom.dis.shared.DocumentViewFileTypes;
import si.telekom.dis.shared.ExplorerService;
import si.telekom.dis.shared.ExplorerServiceAsync;
import si.telekom.dis.shared.LoginService;
import si.telekom.dis.shared.LoginServiceAsync;

public class DocumentViewCollabora extends WindowBox {
	final ListBox renditions;
	FormPanel form;
	Frame frame;
	FlowPanel fp;
	String r_object_id;
	private final static ExplorerServiceAsync explorerService = GWT.create(ExplorerService.class);
	private final static LoginServiceAsync loginService = GWT.create(LoginService.class);
	private final static Logger logger = java.util.logging.Logger.getLogger("mylogger");
	ScrollPanel sp;
	String action;

	public DocumentViewCollabora(String r_object_id_, String action_) {
		r_object_id = r_object_id_;
		this.action = action_;

		// setText("Vsebina dokumenta (barkoda: " +
		// MenuPanel.activeExplorerInstance.selectedDocument.object_name + ")");
		if(action.equals("edit"))
			setText("Urejanje dokumenta (r_object_id: " + r_object_id_ + ")");
		else
			setText("Prikaz vsebine dokumenta (r_object_id: " + r_object_id_ + ")");

		setGlassEnabled(true);

		sp = new ScrollPanel();

		renditions = new ListBox();
		renditions.addChangeHandler(new ChangeHandler() {
			@Override
			public void onChange(ChangeEvent event) {
				refresh();
			}
		});

		try {
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
		} catch (Exception ex) {

		}

		HorizontalPanel hp = new HorizontalPanel();
		hp.add(new Label("Rendicija"));
		hp.add(renditions);

		Button buttonDownload = new Button("Prenesi");
		buttonDownload.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				String url = getUrl(r_object_id, renditions.getSelectedItemText()) + "&download=true";
				String safeUriDocView = UriUtils.fromString(url).asString();
				Window.open(URL.encode(safeUriDocView), "_blank", "status=0,toolbar=0,menubar=0,location=0");
			}
		});
		hp.add(buttonDownload);

		getContentPanel().add(hp);
		getContentPanel().add(sp);

		Scheduler.get().scheduleDeferred(new ScheduledCommand() {
			@Override
			public void execute() {
				DocumentViewCollabora.this.center();
			}
		});

	}

	private void refresh() {
		String rendition = renditions.getSelectedItemText();
		String safeUriDocView = UriUtils.fromString(getUrl(r_object_id, rendition)).asString();

		List<String> odfFormats = DocumentViewFileTypes.odfFormats;

		if (odfFormats.contains(rendition) && MainPanel.getInstance().us.useColaboraOnlineForEdit) {
			String url = getCollabraUrl(r_object_id, rendition);
			//MainPanel.log(url);

			/*
			 * <form id="office_form" name="office_form" target="office_frame"
			 * action="{{ server }}" method="post"> <input name="access_token"
			 * value="{{ access_token }}" type="hidden" /> <input
			 * name="access_token_ttl" value="{{ access_token_ttl }}" type="hidden" />
			 * </form>
			 */
			FormPanel form = new FormPanel();
			form.getElement().setAttribute("name", "office_form");
			form.getElement().setAttribute("id", "office_form");
			form.getElement().setAttribute("target", "office_frame");
			form.setMethod("post");
			form.setAction(url);
			form.setVisible(false);

			Hidden access_token = new Hidden();
			access_token.setValue(b64encode(MainPanel.getInstance().loginName + ":" + MainPanel.getInstance().loginPass + ":" + action));
			access_token.getElement().setAttribute("name", "access_token");
			Hidden access_token_ttl = new Hidden();
			access_token_ttl.setValue("10000000");
			access_token_ttl.getElement().setAttribute("name", "access_token_ttl");

			Div div = new Div();
			div.add(access_token);
			div.add(access_token_ttl);

			form.add(div);

			frame = new Frame();
			frame.getElement().setAttribute("name", "office_frame");
			frame.getElement().setAttribute("id", "office_frame");
			frame.getElement().setAttribute("name", "office_frame");
			frame.getElement().setAttribute("id", "office_frame");

			frame.setTitle("Office Frame");
			frame.getElement().setAttribute("allowfullscreen", "true");
			frame.getElement().setAttribute("sandbox",
					"allow-scripts allow-same-origin allow-forms allow-popups allow-top-navigation allow-popups-to-escape-sandbox allow-downloads allow-modals");

			frame.setWidth(getMaxWidthPx());
			frame.setHeight(getMaxHeightPx());

			Scheduler.get().scheduleDeferred(new ScheduledCommand() {
				@Override
				public void execute() {
					DocumentViewCollabora.this.center();
				}
			});

			fp = new FlowPanel();
			fp.add(form);
			fp.add(frame);

			if (sp.getWidget() != null)
				sp.remove(sp.getWidget());
			sp.add(fp);
			form.submit();

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
					DocumentViewCollabora.this.center();
				}
			});
			image.prefetch(safeUriDocView);
			image.setUrl(safeUriDocView);
			sp.add(image);
			MainPanel.log("Image loading...");
			Scheduler.get().scheduleDeferred(new ScheduledCommand() {
				@Override
				public void execute() {
					DocumentViewCollabora.this.center();
				}
			});
		} else if (DocumentViewFileTypes.downloadFormats.contains(rendition)) {
			// Window.open(URL.encode(safeUriDocView), "_blank",
			// "status=0,toolbar=0,menubar=0,location=0");
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

								// HTML html = new HTML(new
								// SafeHtmlBuilder().appendEscapedLines(response.getText()).toSafeHtml());

								HTML html = new HTML(response.getText());
								sp.add(html);
								DocumentViewCollabora.this.center();
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

	private String getCollabraUrl(String r_object_id2, String rendition) {
		String wopiSrc = GWT.getHostPageBaseURL() + "api/wopi/files/" + r_object_id2;
		wopiSrc = wopiSrc.replaceAll("localhost", MainPanel.getInstance().serverIp);
		//String url = "https://" + MainPanel.getInstance().serverIp + ":9980/loleaflet/d12ab86/loleaflet.html?WOPISrc=" + URL.encodeQueryString(wopiSrc);
		String url = MainPanel.getInstance().us.collaboraUrl + URL.encodeQueryString(wopiSrc);
		//MainPanel.log(url);
		return url;
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

	private static native String b64decode(String a) /*-{
		return window.atob(a);
	}-*/;

	private static native String b64encode(String a) /*-{
	return window.btoa(a);
}-*/;	
	
}
