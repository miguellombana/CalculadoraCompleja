package dad.CalculadoraCompleja;

import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.converter.NumberStringConverter;

public class calculadora extends Application {

    public void start(Stage primaryStage) throws Exception {

        // UI instancias
        VBox vox1 = new VBox();
        VBox vox3 = new VBox();
        VBox vox2 = new VBox();
        HBox hb1 = new HBox();
        HBox hb2 = new HBox();
        HBox hb3 = new HBox();
        HBox hb4 = new HBox();
        Separator sep1 = new Separator();
        TextField op1Text = new TextField();
        TextField op2Text = new TextField();
        TextField op3Text = new TextField();
        TextField op4Text = new TextField();
        TextField result = new TextField();
        TextField result2 = new TextField();
        ComboBox<String> operadorCombo = new ComboBox<>();
        operadorCombo.getItems().addAll("+", "-", "*", "/");

        // Ajustes de controles
        op1Text.setPromptText("0");
        op2Text.setPromptText("0");
        op3Text.setPromptText("0");
        op4Text.setPromptText("0");
        result.setPromptText("0");
        result2.setPromptText("0");

        hb1.getChildren().addAll(vox1, vox2, vox3);
        hb1.setAlignment(Pos.CENTER);

        vox1.getChildren().addAll(operadorCombo);
        vox1.setAlignment(Pos.CENTER);

        vox2.getChildren().addAll(hb2, hb3, sep1, hb4);
        vox2.setAlignment(Pos.CENTER);

        hb2.getChildren().addAll(op1Text, new Label(" +"), op2Text, new Label("i"));
        hb3.getChildren().addAll(op3Text, new Label(" +"), op4Text, new Label("i"));
        hb4.getChildren().addAll(result, new Label(" +"), result2, new Label("i"));

        vox3.setAlignment(Pos.CENTER);

        Scene scene = new Scene(hb1, 500, 500);

        primaryStage.setScene(scene);
        primaryStage.show();
        primaryStage.setResizable(true);

        // Crear objetos de la clase Complejo para los valores de entrada y resultado
        Complejo complejo1 = new Complejo();
        Complejo complejo2 = new Complejo();
        Complejo resultado = new Complejo();

        // Vincular propiedades de los objetos Complejo a los campos de texto
        op1Text.textProperty().bindBidirectional(complejo1.getRealProperty(), new NumberStringConverter());
        op2Text.textProperty().bindBidirectional(complejo1.getImaginarioProperty(), new NumberStringConverter());
        op3Text.textProperty().bindBidirectional(complejo2.getRealProperty(), new NumberStringConverter());
        op4Text.textProperty().bindBidirectional(complejo2.getImaginarioProperty(), new NumberStringConverter());

        // Vincular el resultado a los campos de texto usando bindings
        result.textProperty().bind(Bindings.createStringBinding(() -> {
            String operador = operadorCombo.getValue();

            switch (operador) {
                case "+":
                    resultado.setReal(complejo1.getReal() + complejo2.getReal());
                    resultado.setImaginario(complejo1.getImaginario() + complejo2.getImaginario());
                    break;
                case "-":
                    resultado.setReal(complejo1.getReal() - complejo2.getReal());
                    resultado.setImaginario(complejo1.getImaginario() - complejo2.getImaginario());
                    break;
                case "*":
                    resultado.setReal(complejo1.getReal() * complejo2.getReal() - complejo1.getImaginario() * complejo2.getImaginario());
                    resultado.setImaginario(complejo1.getReal() * complejo2.getImaginario() + complejo1.getImaginario() * complejo2.getReal());
                    break;
                case "/":
                    double divisor = complejo2.getReal() * complejo2.getReal() + complejo2.getImaginario() * complejo2.getImaginario();
                    double realDivision = (complejo1.getReal() * complejo2.getReal() + complejo1.getImaginario() * complejo2.getImaginario()) / divisor;
                    double imagDivision = (complejo1.getImaginario() * complejo2.getReal() - complejo1.getReal() * complejo2.getImaginario()) / divisor;
                    resultado.setReal(realDivision);
                    resultado.setImaginario(imagDivision);
                    break;
            }

            return String.valueOf(resultado.getReal());
        }, complejo1.getRealProperty(), complejo1.getImaginarioProperty(),
            complejo2.getRealProperty(), complejo2.getImaginarioProperty(), operadorCombo.valueProperty()));

        result2.textProperty().bind(resultado.getImaginarioProperty().asString());

        operadorCombo.getSelectionModel().selectFirst();
    }

}
