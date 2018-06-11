package ayds.dictionary.bravo.Model;


//servicios def para que recorra el enumerado, no el repositorio
//le pedimos a servicio def obtener termino de fuente, pasandole la fuente
//repositorio solo conoce a servicio def, no a repositorio a
//el get value del enum lo hace servicios def
public enum Source
{
    WIKIPEDIA,
    YANDEX
}
