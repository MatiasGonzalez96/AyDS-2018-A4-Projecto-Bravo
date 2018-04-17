package ayds.dictionary.bravo.fulllogic.View;

public class TranslateHelperImpl implements TranslateHelper
{
    public TranslateHelperImpl()
    {

    }

    public String textToHtml(String text, String term)
    {
        StringBuilder builder = new StringBuilder();
        String textWithBold = text.replaceAll("(?i)" + term, "<b>" + term + "</b>");
        builder.append(textWithBold);
        return builder.toString();
    }
}
