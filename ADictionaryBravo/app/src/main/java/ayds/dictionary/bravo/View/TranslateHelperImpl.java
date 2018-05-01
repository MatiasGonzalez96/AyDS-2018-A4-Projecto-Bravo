package ayds.dictionary.bravo.View;

class TranslateHelperImpl implements TranslateHelper
{
    TranslateHelperImpl(){}

    public String textToHtml(String text, String term)
    {
        StringBuilder builder = new StringBuilder();
        String textWithBold = text.replaceAll("(?i)" + term, "<b>" + term + "</b>");
        builder.append(textWithBold);
        return builder.toString();
    }
}
