package si.telekom.dis.client;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.logging.Logger;
import java.util.regex.Pattern;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.FocusEvent;
import com.google.gwt.event.dom.client.FocusHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyDownEvent;
import com.google.gwt.event.dom.client.KeyDownHandler;
import com.google.gwt.event.logical.shared.HasValueChangeHandlers;
import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.regexp.shared.MatchResult;
import com.google.gwt.regexp.shared.RegExp;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.MultiWordSuggestOracle;
import com.google.gwt.user.client.ui.SuggestBox;
import com.google.gwt.user.client.ui.SuggestOracle;
import com.google.gwt.user.client.ui.SuggestOracle.Callback;
import com.google.gwt.user.client.ui.SuggestOracle.Request;
import com.google.gwt.user.client.ui.SuggestOracle.Response;
import com.google.gwt.user.client.ui.SuggestOracle.Suggestion;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

import si.telekom.dis.client.action.DocumentProperties;
import si.telekom.dis.shared.Attribute;
import si.telekom.dis.shared.ExplorerService;
import si.telekom.dis.shared.ExplorerServiceAsync;

public class MultiValueSelectBox extends Composite implements HasValue<List<String>>, HasValueChangeHandlers<List<String>> {
	private SuggestBox sb;
	private Attribute att;
	private final static ExplorerServiceAsync explorerService = GWT.create(ExplorerService.class);
	private final static Logger logger = java.util.logging.Logger.getLogger("mylogger");
	TextBox tb;
	ListBox lb;
	private final List<MySuggestion> lastSuggestions;
	VerticalPanel vp;

	public MultiValueSelectBox(Attribute att_, ListBox lb_) {
		this.att = att_;
		this.lastSuggestions = new ArrayList<MySuggestion>();

		vp = new VerticalPanel();

		// tb = new TextBox();
		// tb.addKeyPressHandler(new KeyPressHandler() {
		//
		// @Override
		// public void onKeyPress(KeyPressEvent event) {
		// // TODO Auto-generated method stub
		// if (event.getNativeEvent().getKeyCode() == KeyCodes.KEY_ENTER) {
		// MultiValueSelectBox.this.
		// }
		// }
		// });
		this.lb = lb_;
		this.tb = new TextBox();
		tb.setWidth("100%");
		lb.setWidth("100%");

		MultiWordSuggestOracle oracle = new MyMultiWordSuggestOracle();
		tb.addKeyDownHandler(new KeyDownHandler() {

			@Override
			public void onKeyDown(KeyDownEvent event) {
				if (event.getNativeKeyCode() == KeyCodes.KEY_ENTER) {
					if (!att.isLimitedToValueList && !tb.getText().equals("")) {
						lb.addItem(tb.getText());
						return;
					}
				}
				if (event.getNativeKeyCode() != KeyCodes.KEY_DOWN && event.getNativeKeyCode() != KeyCodes.KEY_UP) {
					if (tb.getText().equals("")) {
						oracle.requestDefaultSuggestions(new Request(null, 1000), new Callback() {
							public void onSuggestionsReady(Request request, Response response) {
								lastSuggestions.clear();
								lastSuggestions.addAll((Collection<? extends MySuggestion>) response.getSuggestions());
							}
						});
					} else {
						oracle.requestSuggestions(new Request(tb.getText(), 1000), new Callback() {
							public void onSuggestionsReady(Request request, Response response) {
								lastSuggestions.clear();
								lastSuggestions.addAll((Collection<? extends MySuggestion>) response.getSuggestions());
							}
						});
					}

				}

			}
		});

		tb.addFocusHandler(new FocusHandler() {
			@Override
			public void onFocus(FocusEvent event) {
				// TODO Auto-generated method stub
				oracle.requestDefaultSuggestions(new Request(null, 1000), new Callback() {
					public void onSuggestionsReady(Request request, Response response) {
						lastSuggestions.clear();
						lastSuggestions.addAll((Collection<? extends MySuggestion>) response.getSuggestions());
					}
				});
				sb.showSuggestionList();
			}
		});

		sb = new SuggestBox(oracle, tb, new SuggestBox.DefaultSuggestionDisplay()) {
			@Override
			public void setText(String text) {
				if (att.isRepeating)
					super.setText("");
				else
					super.setText(text);
			}
		};

		sb.addSelectionHandler(new SelectionHandler<Suggestion>() {
			@Override
			public void onSelection(SelectionEvent<Suggestion> event) {
				MySuggestion sugg = (MySuggestion) event.getSelectedItem();
				// tb.setValue(sugg.getReplacementString());
				if (!MultiValueSelectBox.this.att.isRepeating)
					lb.clear();
				// lb.addItem(sugg.getReplacementString());
				String txt = split(sugg.getRow(), "|")[att.dropDownCol];
				lb.addItem(txt, sugg.getRow());

				if (MultiValueSelectBox.this.getValue() != null) {
					logger.info("Firing change event");
					ValueChangeEvent.fire(MultiValueSelectBox.this, MultiValueSelectBox.this.getValue());
				}
			}
		});
		sb.setAutoSelectEnabled(true);

		vp.add(sb);
		if (att.isRepeating()) {
			vp.add(lb);
			lb.setVisibleItemCount(5);
		}

		sb.setWidth("100%");
		tb.setWidth("100%");
		vp.setWidth("100%");
		initWidget(vp);
	}

	class MyTimer extends Timer {
		Request request;
		Callback callback;
		private MyMultiWordSuggestOracle myMultiWordSuggestOracle;

		public MyTimer instance;

		public MyTimer(MyMultiWordSuggestOracle myMultiWordSuggestOracle_) {
			instance = this;
			this.myMultiWordSuggestOracle = myMultiWordSuggestOracle_;
		}

		@Override
		public void run() {
			myMultiWordSuggestOracle.refresh(request, callback);
		}

		public void set(Request request_, Callback callback_) {
			this.cancel();
			this.schedule(500);
			this.request = request_;
			this.callback = callback_;
		}
	};

	private class MyMultiWordSuggestOracle extends MultiWordSuggestOracle {
		MyTimer timer;

		public MyMultiWordSuggestOracle() {
			super();
			timer = new MyTimer(this);

			this.setComparator(new Comparator<String>() {

				@Override
				public int compare(String o1, String o2) {
					// TODO Auto-generated method stub
					return o1.compareTo(o2);
				}
			});
		}

		@Override
		public void requestDefaultSuggestions(final Request request, final Callback callback) {
			timer.set(request, callback);
		}

		@Override
		public void requestSuggestions(final Request request, final Callback callback) {
			timer.set(request, callback);
		}

		public void refresh(Request request, Callback callback) {
			final String query = request.getQuery() == null ? "" : request.getQuery();

			if (att.dqlValueListDefinition != null) {
				explorerService.getValuesFromDql(MainPanel.getInstance().loginName, MainPanel.getInstance().loginPass, query, att.dqlValueListDefinition,
						new AsyncCallback<List<String[]>>() {
							@Override
							public void onFailure(Throwable caught) {
								MainPanel.log(caught.getMessage());
							}

							@Override
							public void onSuccess(List<String[]> result) {
								ArrayList<Suggestion> al = new ArrayList<Suggestion>();
								for (String[] value : result) {
									String row = "";
									for (String val : value) {
										row = row + val + "|";
									}
									if (row.length() > 0)
										row = row.substring(0, row.length() - 1);
									MySuggestion suggestion = new MySuggestion(row, query);
									al.add(suggestion);
								}
								Response resp = new Response(al);
								callback.onSuggestionsReady(request, resp);
							}
						});
			} else if (att.commaSeparatedValueListDefinition != null) {
				String[] commaSepVals = att.commaSeparatedValueListDefinition.split(",");
				ArrayList<MySuggestion> al = new ArrayList<MySuggestion>();
				for (String string : commaSepVals) {
					if (string.contains(query)) {
						MySuggestion mys = new MySuggestion(string + "|" + string, query);
						al.add(mys);
					}
				}
				callback.onSuggestionsReady(request, new Response(al));
			} else if (att.restQuery != null) {
				explorerService.getValuesFromRest(MainPanel.getInstance().loginName, MainPanel.getInstance().loginPass, query, att.restQuery,
						new AsyncCallback<List<String[]>>() {
							@Override
							public void onFailure(Throwable caught) {
								MainPanel.log(caught.getMessage());
							}

							@Override
							public void onSuccess(List<String[]> result) {
								if (result != null) {
									ArrayList<Suggestion> al = new ArrayList<Suggestion>();
									for (String[] value : result) {
										String row = "";
										for (String val : value) {
											row = row + val + "|";
										}
										if (row.length() > 0)
											row = row.substring(0, row.length() - 1);
										MySuggestion suggestion = new MySuggestion(row, query);
										al.add(suggestion);
									}
									Response resp = new Response(al);
									callback.onSuggestionsReady(request, resp);
								}
							}
						});
			} else if (att.jdbcValueListDefinition != null) {

				// RegExp regExp = RegExp.compile("\\$\\w+", Pattern.MULTILINE |
				// Pattern.CASE_INSENSITIVE);
				RegExp regExp = RegExp.compile("\\$\\w+");

				String jdbcValueListDefinitionFilled = att.jdbcValueListDefinition;

				MatchResult matchResult = regExp.exec(jdbcValueListDefinitionFilled);
				int loopCount = 0;
				while (matchResult != null) {
					String dependantAtt = matchResult.getGroup(0).substring(1, matchResult.getGroup(0).length());
					String replacement = DocumentProperties.getInstance().getAttributeValue(dependantAtt);
					if (replacement != null) {
						jdbcValueListDefinitionFilled = jdbcValueListDefinitionFilled.replaceAll(matchResult.getGroup(0).replaceAll("\\$", "\\\\$"), replacement);
						matchResult = regExp.exec(jdbcValueListDefinitionFilled);
					}
					loopCount++;
					if(loopCount>100)
						break;
				}

				explorerService.getValuesFromSql(MainPanel.getInstance().loginName, MainPanel.getInstance().loginPass, query, jdbcValueListDefinitionFilled,
						new AsyncCallback<List<String[]>>() {
							@Override
							public void onFailure(Throwable caught) {
								MainPanel.log(caught.getMessage());
							}

							@Override
							public void onSuccess(List<String[]> result) {
								if (result != null) {
									ArrayList<Suggestion> al = new ArrayList<Suggestion>();
									for (String[] value : result) {
										String row = "";
										for (String val : value) {
											row = row + val + "|";
										}
										if (row.length() > 0)
											row = row.substring(0, row.length() - 1);
										MySuggestion suggestion = new MySuggestion(row, query);
										al.add(suggestion);
									}
									Response resp = new Response(al);
									callback.onSuggestionsReady(request, resp);
								}
							}
						});
			}
		}

		@Override
		public boolean isDisplayStringHTML() {
			return true;
		}
	}

	public class MySuggestion implements SuggestOracle.Suggestion {
		String row;
		String displ;
		String repl;

		public MySuggestion(String row_, String query) {
			this.row = row_;

			int begin = row_.toLowerCase().indexOf(query.toLowerCase());
			if (begin >= 0) {
				int end = begin + query.length();
				String match = row_.substring(begin, end);
				displ = row_.replaceFirst(match, "<b>" + match + "</b>");
			} else {
				// may not necessarily be a part of the query, for example if "*" was
				// typed.
				displ = row_;
			}

			repl = split(row_, "|")[MultiValueSelectBox.this.att.dropDownCol];
		}

		@Override
		public String getReplacementString() {
			return repl;
		}

		@Override
		public String getDisplayString() {
			return displ;
		}

		public String getRow() {
			return row;
		}
	}

	public void setEnabled(boolean b) {
		tb.setEnabled(false);
	}

	public List<MySuggestion> getSuggestions() {
		return lastSuggestions;
	}

	public void select(String text) {
		for (MySuggestion s : lastSuggestions) {
			if (s.getDisplayString().equals(text)) {
				tb.setText(s.getRow());
				lb.addItem(s.getRow());
				return;
			}
		}
		throw new IllegalArgumentException("Suggestion " + text + " wasn't found");
	}

	@Override
	public ArrayList<String> getValue() {
		ArrayList<String> ret = new ArrayList<String>();
		for (int i = 0; i < lb.getItemCount(); i++) {
			String value = lb.getValue(i);
			ret.add(value);
		}
		return ret;
	}

	@Override
	public void setValue(List<String> alValue) {
		lb.clear();
		for (String strValue : alValue) {
			lb.addItem(strValue);
		}
		if (alValue.size() > 0 && !this.att.isRepeating())
			tb.setText(alValue.get(0));
	}

	@Override
	public void setValue(List<String> value, boolean fireEvents) {
		lb.clear();
		for (String strValue : value) {
			lb.addItem(strValue);
		}
	}

	@Override
	public HandlerRegistration addValueChangeHandler(ValueChangeHandler<List<String>> handler) {
		return this.addHandler(handler, ValueChangeEvent.getType());
	}

	public TextBox getTextBox() {
		return this.tb;
	}

	public static final native String[] split(String string, String separator) /*-{
		return string.split(separator);
	}-*/;

	@Override
	public void setWidth(String width) {
		// vp.setWidth("100%");
		// vp.setCellWidth(tb, "100%");
		// vp.setCellWidth(sb, "100%");
		// vp.setCellWidth(lb, "100%");
		// tb.setWidth("100%");
		// if (lb != null) {
		// lb.setWidth("100%");
		// }
	}
}
