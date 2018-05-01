package ayds.dictionary.bravo.View;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import ayds.dictionary.bravo.R;
import ayds.dictionary.bravo.Controller.DictionaryControllerModule;
import ayds.dictionary.bravo.Controller.EditDictionaryController;
import ayds.dictionary.bravo.Model.DictionaryErrorListener;
import ayds.dictionary.bravo.Model.DictionaryModel;
import ayds.dictionary.bravo.Model.DictionaryModelListener;
import ayds.dictionary.bravo.Model.DictionaryModelModule;

public class MainActivity extends AppCompatActivity
{
  private EditText inputText;
  private Button goButton;
  private TextView definitionPanel;
  private DictionaryModel dictionaryModel;
  private ayds.dictionary.bravo.View.TranslateHelper translateHelper;
  private EditDictionaryController editDictionaryController;

  @Override
  protected void onCreate(Bundle savedInstanceState)
  {
    super.onCreate(savedInstanceState);
    DictionaryControllerModule.getInstance().saveContextAndStartController(getApplicationContext());
    translateHelper = DictionaryViewModule.getInstance().getTranslateHelper();
    editDictionaryController = DictionaryViewModule.getInstance().getEditDictionaryController();
    dictionaryModel= DictionaryModelModule.getInstance(getApplicationContext()).getDictionaryModel();
    setContentView(R.layout.activity_main);
    initComponents();
    initListeners();
  }

  private void initComponents()
  {
    inputText = findViewById(R.id.textField1);
    goButton = findViewById(R.id.goButton);
    definitionPanel = findViewById(R.id.textPane1);

  }

  private void initListeners()
  {
    goButton.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View view) {
        new Thread(new Runnable() {
          public void run() {

              editDictionaryController.searchTerm(inputText.getText().toString());
            }

        }).start();
      }
    });

    dictionaryModel.setModelListener(new DictionaryModelListener()
    {
      @Override
      public void didUpdateDefinition(String lastDefinition) {
        insertDefinition(lastDefinition);
      }
    });

    dictionaryModel.setErrorListener(new DictionaryErrorListener()
    {
      @Override
      public void didFindError() {
        Log.e("**","estoy en el oyente");
        showError();
      }
    });
  }

  private void insertDefinition(final String lastDefinition)
  {
    if (lastDefinition != null)
    {
      runOnUiThread(new Runnable()
      {
        public void run()
        {
          final String outputText = translateHelper.textToHtml(lastDefinition, inputText.getText().toString());
          definitionPanel.setText(Html.fromHtml(outputText));
        }});
    }
  }

  private void showError()
  {
    runOnUiThread(new Runnable()
    {
      public void run()
      {

        Toast.makeText(getApplicationContext(),"Error. Por favor chequee el termino ingresado y/o su conexion",Toast.LENGTH_LONG).show();
        definitionPanel.setText("");
      }
    });
  }
}
