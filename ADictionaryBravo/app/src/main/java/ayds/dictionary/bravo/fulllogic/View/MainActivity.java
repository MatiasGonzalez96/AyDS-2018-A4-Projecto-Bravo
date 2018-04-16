package ayds.dictionary.bravo.fulllogic.View;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import ayds.dictionary.bravo.R;
import ayds.dictionary.bravo.fulllogic.Controller.DictionaryControllerModule;
import ayds.dictionary.bravo.fulllogic.Controller.EditDictionaryController;
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
        new Thread(new Runnable() {
          public void run() {
            editDictionaryController.buscarTermino(textField1.getText().toString());

          }
        }).start();

      }
    });

    dictionaryModel.setListener(new DictionaryModelListener() {
      @Override
      public void didUpdateDictionary(String lastDef) {
        insertarDefinicion(lastDef);
      }
    });
  }

  private void insertarDefinicion(final String lastDef) {
    Log.e("**", "LLEGO "+lastDef);
    //textPane1.setText(lastDef);
    if (lastDef !=null)
    {
      runOnUiThread(new Runnable() {
        public void run() {
          textPane1.setText(Html.fromHtml(textToHtml(lastDef, textField1.getText().toString())));
        }});
    }
  }

  public static String textToHtml(String text, String term) {

        StringBuilder builder = new StringBuilder();

        String textWithBold = text.replaceAll("(?i)" + term, "<b>" + term + "</b>");

        builder.append(textWithBold);

        return builder.toString();
    }
}
