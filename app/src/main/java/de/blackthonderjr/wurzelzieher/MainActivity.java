package de.blackthonderjr.wurzelzieher;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.Html;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText numberInput, stepsInput;
    private TextView resultLabel, calculationView;
    private CheckBox helpCheckbox, moreHelpCheckbox;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        numberInput = findViewById(R.id.numberInput);
        stepsInput = findViewById(R.id.stepsInput);
        resultLabel = findViewById(R.id.resultLabel);
        calculationView = findViewById(R.id.calculationView);
        Button calculateButton = findViewById(R.id.calculateButton);
        helpCheckbox = findViewById(R.id.helpCheckbox);
        moreHelpCheckbox = findViewById(R.id.moreHelpCheckbox);

        calculateButton.setOnClickListener(v -> {
            String numberStr = numberInput.getText().toString().replace(",", ".");
            String stepsStr = stepsInput.getText().toString();

            if (TextUtils.isEmpty(numberStr) || TextUtils.isEmpty(stepsStr)) {
                resultLabel.setText("Ungültige Eingabe!");
                resultLabel.setTextColor(getResources().getColor(android.R.color.holo_red_light));
                return;
            }

            try {
                double number = Double.parseDouble(numberStr);
                int steps = Integer.parseInt(stepsStr);

                if (steps > 50) {
                    resultLabel.setText("Max. Schritte: 50");
                    resultLabel.setTextColor(getResources().getColor(android.R.color.holo_red_light));
                    return;
                }

                Rechner.clearRechnung();
                String result = Rechner.rechnung(number, steps);
                resultLabel.setText("ERGEBNIS: " + result);
                resultLabel.setTextColor(getResources().getColor(android.R.color.holo_green_light));
                calculationView.setText(Rechner.getRechnung());
            } catch (NumberFormatException e) {
                resultLabel.setText("Ungültige Eingabe!");
                resultLabel.setTextColor(getResources().getColor(android.R.color.holo_red_light));
            }
        });

        helpCheckbox.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                moreHelpCheckbox.setChecked(false);
                calculationView.setText("Hilfe:\n" +
                        "Das Ziehen der Quadratwurzel ist das Gegenteil des Quadrierens. " +
                        "Beispiel: Die Wurzel aus 9 ist 3, da 3 × 3 = 9. Gebe die Zahl und die Anzahl der Schritte ein, " +
                        "um die Wurzel zu berechnen.");
            } else {
                calculationView.setText("");
            }
        });

        moreHelpCheckbox.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                helpCheckbox.setChecked(false);
                String moreHelpText = "<b>Weitere Hilfe:</b>" +
                        "<p align='center'><b>Wiki:</b></p>" +
                        "<a href='https://www.matheretter.de/wiki/wurzel'>Matheretter</a><br><br>" +
                        "<p align='center'><b>Youtube:</b></p>" +
                        "<a href='https://www.youtube.com/watch?v=MkcArrEhUQI&list=PLv8b855uAKpZyhOycVvoqshsZLYiE3GXJ'>YouTube: Lehrer Schmidt</a><br>" +
                        "<a href='https://www.youtube.com/watch?v=-wEJzyHB5us'>Daniel Jung</a><br>" +
                        "<a href='https://www.youtube.com/watch?v=hV70xFlb99Q'>Schlau wie wow</a>";


                calculationView.setText(Html.fromHtml(moreHelpText));
                calculationView.setMovementMethod(LinkMovementMethod.getInstance());
            } else {
                calculationView.setText("");
            }
        });
    }
}
