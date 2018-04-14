package ayds.dictionary.bravo.fulllogic.View;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import ayds.dictionary.bravo.R;
import ayds.dictionary.bravo.fulllogic.Controller.DictionaryControllerModule;
import ayds.dictionary.bravo.fulllogic.Controller.EditDictionaryController;
import ayds.dictionary.bravo.fulllogic.DataBase;
import ayds.dictionary.bravo.fulllogic.Model.Dictionary;
import ayds.dictionary.bravo.fulllogic.Model.DictionaryModel;
import ayds.dictionary.bravo.fulllogic.Model.DictionaryModelListener;
import ayds.dictionary.bravo.fulllogic.Model.DictionaryModelModule;

public class MainActivity extends AppCompatActivity {

  private EditText textField1;
  private Button goButton;
  private TextView textPane1;
  private DictionaryModel dictionaryModel;

  private EditDictionaryController editDictionaryController;

  @Override
  protected void onCreate(Bundle savedInstanceState)
  {
    super.onCreate(savedInstanceState);
    DictionaryControllerModule.getInstance().startApplication(getApplicationContext());
    editDictionaryController = DictionaryViewModule.getInstance().getEditDictionaryController();
    dictionaryModel= DictionaryModelModule.getInstance(getApplicationContext()).getDictionaryModel();
    setContentView(R.layout.activity_main);
    inicializarComponentes();
    inicializarListeners();
  }

  private void inicializarComponentes()
  {
    textField1 = findViewById(R.id.textField1);
    goButton = findViewById(R.id.goButton);
    textPane1 = findViewById(R.id.textPane1);
  }

  private void inicializarListeners()
  {
    goButton.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View view) {

        editDictionaryController.buscarTermino(textField1.getText().toString());
      }
    });

    dictionaryModel.setListener(new DictionaryModelListener() {
      @Override
      public void didUpdateDictionary(String lastDef) {
        insertarDefinicion(lastDef);
      }
    });
  }

  private void insertarDefinicion(String lastDef) {

    textPane1.setText(textToHtml(lastDef,textField1.getText().toString()));
  }

  public static String textToHtml(String text, String term) {

        StringBuilder builder = new StringBuilder();

        String textWithBold = text.replaceAll("(?i)" + term, "<b>" + term + "</b>");

        builder.append(textWithBold);

        return builder.toString();
    }
}
