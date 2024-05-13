package com.example.examapplication0511;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Arrays;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ConverterFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ConverterFragment extends Fragment {


    private EditText editOperations;
    boolean isFahrenheit = false;
    boolean isResult = false;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ConverterFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ConverterFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ConverterFragment newInstance(String param1, String param2) {
        ConverterFragment fragment = new ConverterFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @SuppressLint("SetTextI18n")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view;
        view = inflater.inflate(R.layout.fragment_converter, container, false);


        editOperations = view.findViewById(R.id.editTextActionBar);

        String Result = calcResult(editOperations.getText().toString());


        Button buttonPlus = view.findViewById(R.id.plusButton);
        Button buttonMinus = view.findViewById(R.id.minusButton);
        Button buttonMultiply = view.findViewById(R.id.multiplyButton);
        Button buttonDivision = view.findViewById(R.id.divisionButton);
        Button buttonDegree = view.findViewById(R.id.degreeButton);
        Button buttonFactorial = view.findViewById(R.id.factorialButton);
        Button buttonSqrt = view.findViewById(R.id.sqrtButton);
        Button buttonResult = view.findViewById(R.id.resultButton);
        Button buttonClear = view.findViewById(R.id.clearButton);
        Button buttonDelete = view.findViewById(R.id.deleteButton);
        Button convertButton = view.findViewById(R.id.convertButton);

        buttonPlus.setOnClickListener(v1 -> {
            editOperations.setText(editOperations.getText() + " + ");
            editOperations.setSelection(editOperations.getText().length());
        });

        buttonMinus.setOnClickListener(v12 -> {
            editOperations.setText(editOperations.getText() + " - ");
            editOperations.setSelection(editOperations.getText().length());
        });

        buttonMultiply.setOnClickListener(v13 -> {
            editOperations.setText(editOperations.getText() + " * ");
            editOperations.setSelection(editOperations.getText().length());
        });

        buttonDivision.setOnClickListener(v14 -> {
            editOperations.setText(editOperations.getText() + " / ");
            editOperations.setSelection(editOperations.getText().length());
        });

        buttonDegree.setOnClickListener(v15 -> {
            editOperations.setText(editOperations.getText() + " ^ ");
            editOperations.setSelection(editOperations.getText().length());
        });

        buttonFactorial.setOnClickListener(v16 -> {
            editOperations.setText(editOperations.getText() + " ! ");
            editOperations.setSelection(editOperations.getText().length());
        });

        buttonSqrt.setOnClickListener(v17 -> {
            editOperations.setText(editOperations.getText() + " sqrt ");
            editOperations.setSelection(editOperations.getText().length());
        });
        buttonResult.setOnClickListener(v18 -> {
            if (isResult) {

                String Result1 = calcResult(editOperations.getText().toString());
                float Results = (float) Math.round(Float.parseFloat(Result1) * 1000) / 1000;
                editOperations.setText(editOperations.getText() + " = " + Results);
                editOperations.setSelection(editOperations.getText().length());
                isResult = false;
            } else {
                editOperations.setText("");
            }
        });
        buttonClear.setOnClickListener(v19 -> {
            editOperations.setText("");
            isResult = true;
        });
        convertButton.setOnClickListener(v20 -> {
            String text = editOperations.getText().toString();
            if (!text.isEmpty()) {
                text = convertToFahrenheitCelsius(text);
                editOperations.setText(text);
                editOperations.setSelection(editOperations.getText().length());
                isResult = true;
            }
        });
        buttonDelete.setOnClickListener(v110 -> {
            String text = editOperations.getText().toString();
            if (!text.isEmpty()) {
                text = text.substring(0, text.length() - 1);
                editOperations.setText(text);
                editOperations.setSelection(editOperations.getText().length());
                isResult = true;
            }
        });
        return view;
    }

    @SuppressLint("SetTextI18n")
    private String convertToFahrenheitCelsius(String text) {
        isResult = true;
        Button btnConvert = getView().findViewById(R.id.convertButton);
        if (isFahrenheit) {
            isFahrenheit = false;
            btnConvert.setText("Convert to Fahrenheit");
            return String.valueOf((Double.parseDouble(text) - 32) * 5 / 9);
        } else {
        isFahrenheit = true;
        btnConvert.setText("Convert to Celsius");
            return String.valueOf(Double.parseDouble(text) * 9 / 5 + 32);
        }
    }

    private String calcResult(String operations) {
        String[] actions = operations.split(" ");
        Log.d("Actions", Arrays.toString(actions));
        double result = 0;
        isResult = true;
        for (int i = 0; i < actions.length; i++) {
            if (actions[i].equals("+")) {
                Log.d("Actions", "i " + actions[i]);
                result = Double.parseDouble(actions[0]) + Double.parseDouble(actions[i + 1]);
            } else if (actions[i].equals("-")) {
                result = Double.parseDouble(actions[0]) - Double.parseDouble(actions[i + 1]);
            } else if (actions[i].equals("*")) {
                result = Double.parseDouble(actions[0]) * Double.parseDouble(actions[i + 1]);
            } else if (actions[i].equals("/")) {
                result = Double.parseDouble(actions[0]) / Double.parseDouble(actions[i + 1]);
            } else if (actions[i].equals("%")) {
                result = Double.parseDouble(actions[0]) % Double.parseDouble(actions[i + 1]);
            } else if (actions[i].equals("^")) {
                result = Math.pow(Double.parseDouble(actions[0]), Double.parseDouble(actions[i + 1]));
            } else if (actions[i].equals("sqrt")) {
                result = Math.sqrt(Double.parseDouble(actions[0]));
            } else if (actions[i].equals("!")) {
                result = factorial(Double.parseDouble(actions[0]));
            }
        }
        return String.valueOf(result);
    }

    private double factorial(double result) {
        isResult = true;
        if (result == 0) {
            return 1;
        } else {
            return result * factorial(result - 1);
        }
    }


}