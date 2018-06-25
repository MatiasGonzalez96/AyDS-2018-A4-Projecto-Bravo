package ayds.dictionary.bravo.View;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import java.util.List;
import ayds.dictionary.bravo.Model.Definition;
import ayds.dictionary.bravo.R;
import ayds.dictionary.bravo.Controller.DictionaryControllerModule;
import ayds.dictionary.bravo.Controller.EditDictionaryController;
import ayds.dictionary.bravo.Model.Listener.DictionaryErrorListener;
import ayds.dictionary.bravo.Model.DictionaryModel;
import ayds.dictionary.bravo.Model.Listener.DictionaryModelListener;
import ayds.dictionary.bravo.Model.DictionaryModelModule;

public class MainActivity extends AppCompatActivity
{
  private EditText inputText;
  private Button goButton;
  private TextView definitionPanel, source;
  private ProgressBar progressBar;
  private DictionaryModel dictionaryModel;
  private TranslateHelper translateHelper;
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
    source = findViewById(R.id.title);
    goButton = findViewById(R.id.goButton);
    definitionPanel = findViewById(R.id.textPane1);
    progressBar = findViewById(R.id.progressBar);
  }

  private void initListeners()
  {
    goButton.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View view) {
        progressBar.setVisibility(View.VISIBLE);
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
      public void didUpdateDefinition(List<Definition> lastDefinition) {
        insertDefinition(lastDefinition);
      }
    });

    dictionaryModel.setErrorListener(new DictionaryErrorListener()
    {
      @Override
      public void didFindError(String message) {
        showError(message);
      }
    });
  }

  private void insertDefinition(final List<Definition> lastDefinition)
  {
      String searchedWord = inputText.getText().toString();
      String output = "";
      for (Definition definition : lastDefinition)
      {
          String definitionText = translateHelper.textToHtml(definition.getMeaning(), searchedWord);
          String sourceText = definition.getSource().toString();
          output += sourceText + definitionText;
      }

      final String outputText = output;

      runOnUiThread(new Runnable()
      {
        public void run()
        {
          definitionPanel.setText(Html.fromHtml(outputText));
          inputText.setText("");
          progressBar.setVisibility(View.GONE);
        }
      });
  }

  private void showError(final String message)
  {
    runOnUiThread(new Runnable()
    {
      public void run()
      {
        Toast.makeText(getApplicationContext(),message,Toast.LENGTH_LONG).show();
        definitionPanel.setText("");
        inputText.setText("");
        source.setText("");
        progressBar.setVisibility(View.GONE);
      }
    });
  }
}
