package ayds.dictionary.bravo.fulllogic.Model;

import ayds.dictionary.bravo.fulllogic.Model.BDD.DictionaryDataBase;
import ayds.dictionary.bravo.fulllogic.Model.Servicios.Servicio;


public class RepositorioImpl implements Repositorio
{

    Servicio servicioImpl;
    DictionaryDataBase dataBase;

    public RepositorioImpl(Servicio servicioImpl, DictionaryDataBase dataBase)
    {
        this.servicioImpl=servicioImpl;
        this.dataBase=dataBase;
    }

    public String buscarTermino(final String input) {

            String text = dataBase.getMeaning(input);
            if (text != null) { // exists in db

                text = "[*]" + text;
            }
            else
                {
                    text=servicioImpl.getMeaning(input);
                    dataBase.saveTerm(input, text);
                }


    return text;
    }


}

