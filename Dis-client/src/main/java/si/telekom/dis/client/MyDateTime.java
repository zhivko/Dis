package si.telekom.dis.client;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import com.google.gwt.event.dom.client.BlurEvent;
import com.google.gwt.event.dom.client.BlurHandler;
import com.google.gwt.event.dom.client.FocusEvent;
import com.google.gwt.event.dom.client.FocusHandler;
import com.google.gwt.event.dom.client.HasKeyUpHandlers;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyDownEvent;
import com.google.gwt.event.dom.client.KeyDownHandler;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.event.logical.shared.HasValueChangeHandlers;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.i18n.shared.DateTimeFormat;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

public class MyDateTime extends Composite implements HasValueChangeHandlers<String>, HasKeyUpHandlers {
	private TextBox textBoxDateTime = new TextBox();
	private Date date;
	String germanFormat = "dd.MM.yyyy HH:mm:ss";
	DateTimeFormat f = DateTimeFormat.getFormat(germanFormat);
	String initialValue = "__.__.____ __:__:__";

	private List<ValueChangeHandler<String>> al = new ArrayList<ValueChangeHandler<String>>();

	public MyDateTime() {
		date = null;
		VerticalPanel p = new VerticalPanel();

		textBoxDateTime.addBlurHandler(new BlurHandler() {

			@Override
			public void onBlur(BlurEvent event) {
				validate();
			}
		});

		textBoxDateTime.addFocusHandler(new FocusHandler() {

			@Override
			public void onFocus(FocusEvent event) {
				// TODO Auto-generated method stub

				if (MyDateTime.this.date == null && (!MyDateTime.this.textBoxDateTime.getText().contains(":") || !initialValue.equals(""))) {
					Logger.getGlobal().info("sdf");
					MyDateTime.this.textBoxDateTime.setText(initialValue);
				}
			}
		});

		textBoxDateTime.addKeyDownHandler(new KeyDownHandler() {
			@Override
			public void onKeyDown(KeyDownEvent event) {

				if (event.getNativeKeyCode() == KeyCodes.KEY_TAB)
					return;

				if (event.getNativeKeyCode() == KeyCodes.KEY_DOWN || event.getNativeKeyCode() == KeyCodes.KEY_UP
						|| event.getNativeKeyCode() == KeyCodes.KEY_LEFT || event.getNativeKeyCode() == KeyCodes.KEY_RIGHT) {
					return;
				}

				if (textBoxDateTime.getCursorPos() == 0 || textBoxDateTime.getCursorPos() == 1 || textBoxDateTime.getCursorPos() == 3
						|| textBoxDateTime.getCursorPos() == 4 || textBoxDateTime.getCursorPos() == 6 || textBoxDateTime.getCursorPos() == 7
						|| textBoxDateTime.getCursorPos() == 8 || textBoxDateTime.getCursorPos() == 9 || textBoxDateTime.getCursorPos() == 11
						|| textBoxDateTime.getCursorPos() == 12 || textBoxDateTime.getCursorPos() == 14 || textBoxDateTime.getCursorPos() == 15
						|| textBoxDateTime.getCursorPos() == 17 || textBoxDateTime.getCursorPos() == 18) {
					// day first digit - only accept digit

					int nkc = event.getNativeKeyCode();

					int nkc0 = KeyCodes.KEY_NUM_ZERO;
					int nkc9 = KeyCodes.KEY_NUM_NINE;

					Logger.getGlobal().info("" + nkc);

					String formatedText = textBoxDateTime.getText();
					int curPos = textBoxDateTime.getCursorPos();
					if ((nkc >= 48 && nkc <= 59) || (nkc >= 96 && nkc <= 105)) {
						if (nkc >= 96)
							nkc = nkc - 48;
						String ch = "" + (char) nkc;

						formatedText = formatedText.substring(0, curPos) + ch + formatedText.substring(curPos + 1, formatedText.length());
						textBoxDateTime.setText(formatedText);
						reposition(curPos);
					} else if (event.getNativeKeyCode() == KeyCodes.KEY_DELETE) {
						if (textBoxDateTime.getSelectionLength() == initialValue.length()) {
							formatedText = initialValue;
						} else {
							formatedText = formatedText.substring(0, curPos) + "_" + formatedText.substring(curPos + 1, formatedText.length());
						}
						textBoxDateTime.setText(formatedText);
						textBoxDateTime.setCursorPos(curPos);
					} else if (event.getNativeKeyCode() == KeyCodes.KEY_BACKSPACE) {
						// formatedText = formatedText.substring(0, curPos-1) + "_" +
						// formatedText.substring(curPos, formatedText.length());
						// textBoxDateTime.setText(formatedText);
						// textBoxDateTime.setCursorPos(curPos-1);
						// ((TextBox) event.getSource()).cancelKey();
					} else if (event.getNativeKeyCode() == KeyCodes.KEY_HOME) {
						textBoxDateTime.setCursorPos(0);
					}

				}
				validate();
				((TextBox) event.getSource()).cancelKey();
				event.preventDefault();
				event.stopPropagation();

			}
		});
		p.add(textBoxDateTime);

		textBoxDateTime.setWidth("100%");
		p.setWidth("100%");

		initWidget(p);
	}

	protected void validate() {

		try {
			String txt = textBoxDateTime.getText();
			Logger.getGlobal().info("trying to parse: " + txt);
			if(txt.equals(initialValue))
			{
				textBoxDateTime.getElement().getStyle().setBackgroundColor("");
				this.date = null;
				return;
			}
			
			if (!checkForPartsOK(txt)) {
				textBoxDateTime.getElement().getStyle().setBackgroundColor("red");
				this.date = null;
				return;
			}
			Date newDate = f.parse(txt);
			Logger.getGlobal().info("Parsed to: " + f.format(newDate));
			textBoxDateTime.getElement().getStyle().setBackgroundColor("");
			if (!newDate.equals(this.date)) {
				this.date = newDate;
				valueChanged();
			}
		} catch (Exception ex) {
			Logger.getGlobal().info("error:_ " + ex.toString());
			textBoxDateTime.getElement().getStyle().setBackgroundColor("red");
		}
	}

	private boolean checkForPartsOK(String txt) {
		boolean ret = false;

		try {

			/*
			 * Logger.getGlobal().info(txt.substring(0,2));
			 * Logger.getGlobal().info(txt.substring(3,5));
			 * Logger.getGlobal().info(txt.substring(6,10));
			 * 
			 * Logger.getGlobal().info(txt.substring(11,13));
			 * Logger.getGlobal().info(txt.substring(14,16));
			 * Logger.getGlobal().info(txt.substring(17,19));
			 */

			int dd = Integer.valueOf(txt.substring(0, 2));
			int MM = Integer.valueOf(txt.substring(3, 5));
			int yyyy = Integer.valueOf(txt.substring(6, 10));

			int hh = Integer.valueOf(txt.substring(11, 13));
			int mm = Integer.valueOf(txt.substring(14, 16));
			int ss = Integer.valueOf(txt.substring(17, 19));

			if (dd > 0 && dd < 32)
				if (MM > 0 && MM < 13)
					if (yyyy > 0 && yyyy < 9999)
						if (hh > -1 && hh < 24)
							if (mm > -1 && mm < 60)
								if (ss > -1 && ss < 60)
									ret = true;
		} catch (Exception ex) {
			Logger.getGlobal().info(ex.toString());
		}
		return ret;
	}

	protected void reposition(int curPos) {
		if (germanFormat.substring(curPos + 1, curPos + 2).equals(".") || germanFormat.substring(curPos + 1, curPos + 2).equals(" ")
				|| germanFormat.substring(curPos + 1, curPos + 2).equals(":"))

		{
			if (curPos + 2 < germanFormat.length())
				textBoxDateTime.setCursorPos(curPos + 2);
		} else {
			if (curPos + 1 < germanFormat.length())
				textBoxDateTime.setCursorPos(curPos + 1);
		}
	}

	public void valueChanged() {
		// for (ValueChangeHandler<String> valueChangeHandler : al) {
		ValueChangeEvent.fire(this, f.format(this.date));
		// }
	}

	public void enterPressed() {
		// TODO Auto-generated method stub

	}

	public String getValue() {
		return this.textBoxDateTime.getText();
	}

	public Date getValueDate() {
		return this.date;
	}

	public void setValue(String val) {
		try {
			this.date = f.parse(val);
			this.textBoxDateTime.setText(val);
		} catch (Exception ex) {
			Logger.getGlobal().info(ex.getMessage());
		}

	}

	public void setValue(Date dat) {
		try {
			this.date = dat;
			this.textBoxDateTime.setText(f.format(dat));
		} catch (Exception ex) {
			Logger.getGlobal().info(ex.getMessage());
		}

	}

	@Override
	public HandlerRegistration addValueChangeHandler(ValueChangeHandler<String> handler) {
		// TODO Auto-generated method stub
		al.add(handler);
		HandlerRegistration vch = textBoxDateTime.addValueChangeHandler(handler);
		return vch;
	}

	public void setEnabled(boolean b) {
		// TODO Auto-generated method stub
		textBoxDateTime.setEnabled(b);
	}

	@Override
	public HandlerRegistration addKeyUpHandler(KeyUpHandler handler) {
		// TODO Auto-generated method stub
		return textBoxDateTime.addDomHandler(handler, KeyUpEvent.getType());
	}

}
