package com.resort.booking.formatter;

import javax.swing.*;
import javax.swing.text.InternationalFormatter;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.NumberFormat;

public class RateFormater extends JFormattedTextField.AbstractFormatterFactory {
    @Override
    public JFormattedTextField.AbstractFormatter getFormatter(JFormattedTextField tf) {
        NumberFormat format = DecimalFormat.getInstance();
        format.setMinimumFractionDigits(2);
        format.setMaximumFractionDigits(2);
        format.setRoundingMode(RoundingMode.HALF_UP);
        InternationalFormatter formatter = new InternationalFormatter(format);
        formatter.setAllowsInvalid(false);
        formatter.setMinimum(0.0);
        formatter.setMaximum(100.00);
        return formatter;
    }
}
