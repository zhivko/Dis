package si.telekom.dis.client;

import com.google.gwt.dom.client.Element;
import com.google.gwt.user.client.ui.HTML;

public class ProgressBar extends HTML 
{
    public ProgressBar()
    {
        super("<progress style='width: 98%;'></progress>");
    }
 
    /**
     * Set the progress indicator the the specified values
     * @param value Current progress value
     * @param max Target/complete value
     */
    public void setProgress(int value, int max)
    {
        Element progressElement = getElement().getFirstChildElement();
        progressElement.setAttribute("value", String.valueOf(value));
        progressElement.setAttribute("max", String.valueOf(max));
    }
 
    /**
     * Remove the progress indicator values.  On firefox, this causes the
     * progress bar to sweep back and forth.
     */
    public void clearProgress()
    {
        Element progressElement = getElement().getFirstChildElement();
        progressElement.removeAttribute("value");
        progressElement.removeAttribute("max");
    }
}
