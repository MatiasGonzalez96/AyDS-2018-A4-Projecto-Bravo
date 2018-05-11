package ayds.dictionary.bravo.Model;

public class Definition
{
    private String term;
    private String meaning;
    private Source source;

    public void setTerm(String term)
    {
        this.term = term;
    }

    public void setMeaning(String meaning)
    {
        this.meaning = meaning;
    }

    public void setSource(Source source)
    {
        this.source = source;
    }

    public String getTerm()
    {
        return term;
    }

    public String getMeaning()
    {
        return meaning;
    }

    public Source getSource()
    {
        return source;
    }
}
