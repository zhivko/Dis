package si.telekom.dis.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.user.client.ui.AbstractImagePrototype;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;

public class Spinner extends Composite {

	private Label currentPage;
	private Button maxPage;
	private Button minPage;
	public int value;
	public int min;
	public int max;

	Images images = (Images) GWT.create(Images.class);

	public Spinner(int min_, int max_) {
		super();

		this.min = min_;
		this.max = max_;

		HorizontalPanel hp = new HorizontalPanel();

		minPage = new Button();
		minPage.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				if (!(value - 1 < min)) {
					value--;
					refreshValue();
					valueChanged();
				}
			}
		});

		maxPage = new Button();
		maxPage.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				if (!(value + 1 > max)) {
					value++;
					refreshValue();
					valueChanged();
				}
			}
		});
		buildMinMaxLabels();

		currentPage = new Label();

		Button add = new Button("+");
		add.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				max++;
				value = max;
				added(value);
				refreshValue();
			}
		});

		Button remove = new Button("-");
		remove.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				if (value > min) {
					int oldValue = value;
					value--;
					max--;
					refreshValue();
					removed(oldValue);
				}
			}
		});

		hp.add(remove);
		hp.add(minPage);
		hp.add(currentPage);
		hp.add(maxPage);
		hp.add(add);

		value = min;

		refreshValue();

		initWidget(hp);
	}

	public void valueChanged() {

	}

	public void removed(int which) {

	}

	public void added(int which) {

	}

	public void buildMinMaxLabels() {
		ImageResource imageLeft = images.iconLeft();
		SafeHtmlBuilder itemLeftHtml = new SafeHtmlBuilder();
		itemLeftHtml.append(AbstractImagePrototype.create(imageLeft).getSafeHtml());
		minPage.setHTML(itemLeftHtml.toSafeHtml());

		ImageResource imageRigth = images.iconRight();
		SafeHtmlBuilder itemRightHtml = new SafeHtmlBuilder();
		itemRightHtml.append(AbstractImagePrototype.create(imageRigth).getSafeHtml());
		maxPage.setHTML(itemRightHtml.toSafeHtml());
	}

	public void refreshValue() {
		currentPage.setText(String.valueOf(value) + "/" + String.valueOf(max - min + 1));
		buildMinMaxLabels();
	}

	public void setMax(int max_) {
		this.max = max_;
		refreshValue();
	}
}
