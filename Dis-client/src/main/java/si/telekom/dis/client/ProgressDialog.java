package si.telekom.dis.client;

import com.google.gwt.core.client.Scheduler;
import com.google.gwt.user.client.DeferredCommand;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

public class ProgressDialog extends DialogBox {
	int progress;
	ProgressBar pb;
	Label lbl;
	boolean running=false;

	public ProgressDialog() {
		super();
		pb = new ProgressBar();
		pb.setProgress(1, 100);
		VerticalPanel vp = new VerticalPanel();
		lbl = new Label();
		lbl.setWidth("500px");
		vp.add(pb);
		vp.add(lbl);
		this.add(vp);

		setWidget(vp);
	}

	public ProgressDialog(String txt) {
		this();
		lbl.setText(txt);
	}

	public void showit() {
		RootPanel.get().add(this);
		this.show();
		this.center();
		running = true;

		Scheduler.get().scheduleFixedDelay(new Scheduler.RepeatingCommand() {
			@Override
			public boolean execute() {
				// TODO Auto-generated method stub
				progress++;
				if (progress > 100)
					progress = 1;
				pb.setProgress(progress, 100);

				if(running)
					return true;
				else
					return false;
			}
		}, 1000);

	}

	public void hideit() {
		running = false;
		RootPanel.get().remove(this);
	}

}
