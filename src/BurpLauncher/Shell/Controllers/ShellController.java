package BurpLauncher.Shell.Controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.DirectoryChooser;
import org.eclipse.jgit.api.CloneCommand;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.PullCommand;
import org.eclipse.jgit.api.ResetCommand;

import java.io.File;

public class ShellController {

    @FXML
    private TextField modFolderLocation;
    @FXML
    private TextField gitHubLocation;
    @FXML
    private Label statusLabel;
    @FXML
    private Button syncButton;

    public void onBrowseFolders() {

        DirectoryChooser blah = new DirectoryChooser();
        blah.setTitle("Choose Minecraft Mod Folder");
        File directory = blah.showDialog(null);

        if (directory == null) {
            return;
        }

        modFolderLocation.setText(directory.getAbsolutePath());
    }

    public void onSync() {
        try {
            File directory = new File(modFolderLocation.getText());

            if (!directory.isDirectory()) {
                // TODO: Notify user
                return;
            }
            File gitPath = new File(directory.getPath() + "/.git");


            if (!gitPath.exists()) {
                if(!directory.exists()) {
                    directory.mkdirs();
                }
                else {
                    // clear out files so it can properly clone
                    File[] files = directory.listFiles();

                    for(int i = 0; i < files.length;i++) {
                        files[i].delete();
                    }
                }

                CloneCommand cloneCommand = Git.cloneRepository();
                cloneCommand.setDirectory(directory);
                cloneCommand.setURI(gitHubLocation.getText());

                try {
                    cloneCommand.call();
                } catch (java.lang.Exception e) {
                    statusLabel.setText(e.getMessage());
                    return;
                }
            } else {
                try {
                    Git.open(directory).reset().setMode(ResetCommand.ResetType.HARD).call();
                } catch (java.lang.Exception e) {
                    statusLabel.setText(e.getMessage());
                    return;
                }

                PullCommand pullCommand;

                try {
                    pullCommand = Git.open(directory).pull();

                    pullCommand.call();
                } catch (java.lang.Exception e) {
                    statusLabel.setText(e.getMessage());
                    return;
                }
            }
        } finally {
        }
    }
}