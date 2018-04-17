package ayds.dictionary.bravo.fulllogic.View;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
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

public class MainActivity extends AppCompatActivity
{

  private EditText inputText;
  private Button goButton;
  private TextView textViewPane1;
  private DictionaryModel dictionaryModel;
  private TranslateHelper translateHelper;

  private EditDictionaryController editDictionaryController;

  @Override
  protected void onCreate(Bundle savedInstanceState)
  {
    super.onCreate(savedInstanceState);
    translateHelper = new TranslateHelperImpl();
    DictionaryControllerModule.getInstance().startApplication(getApplicationContext());
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
    textViewPane1 = findViewById(R.id.textPane1);
  }

  private void initListeners()
  {
    goButton.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View view) {
        new Thread(new Runnable() {
          public void run() {
            editDictionaryController.askForTerm(inputText.getText().toString());
          }
        }).start();
      }
    });

    dictionaryModel.setListener(new DictionaryModelListener()
    {
      @Override
      public void didUpdateDictionary(String lastDefinition) {
        insertDefinition(lastDefinition);
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
          textViewPane1.setText(Html.fromHtml(outputText));
        }});
    }
  }
}
