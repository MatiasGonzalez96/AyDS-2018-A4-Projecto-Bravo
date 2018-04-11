package ayds.dictionary.bravo.fulllogic.Controller;

import ayds.dictionary.bravo.fulllogic.MainActivity;

public class DictionaryControllerModule
{
    private static DictionaryControllerModule instance;

    private DictionaryControllerModule() { }

    public static DictionaryControllerModule getInstance() {
        if (instance == null) {
            instance = new DictionaryControllerModule();
        }
        return instance;
    }

    public void startApplication(MainActivity m) {
        EditDictionaryController controller = getEditDictionaryController();

        EditUserView view = openEditUserWindowAndGetView(controller);

        controller.setEditUserView(view);
    }

    private EditDictionaryController getEditDictionaryController() {
        return new EditDictionaryControllerImpl(UserModelModule.getInstance().getUserModel());
    }

    private EditUserView openEditUserWindowAndGetView(EditUserController editUserController) {
        return UserViewModule.getInstance().openEditUserWindow(editUserController);
    }
}
