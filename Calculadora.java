package com.mycompany.calculadora;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Calculadora extends JFrame {

  private JTextField display;
  private JButton[] botoes;
  private double primeiroNumero;
  private char operacao;

  public Calculadora() {
    super("Calculadora");

    setSize(300, 400);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setLayout(new BorderLayout());

    display = new JTextField();
    display.setEditable(false);
    add(display, BorderLayout.NORTH);

    JPanel painelBotoes = new JPanel();
    painelBotoes.setLayout(new GridLayout(4, 4));

    botoes = new JButton[16];
    for (int i = 0; i < 10; i++) {
      botoes[i] = new JButton(String.valueOf(i));
    }
    botoes[10] = new JButton(".");
    botoes[11] = new JButton("+");
    botoes[12] = new JButton("-");
    botoes[13] = new JButton("*");
    botoes[14] = new JButton("/");
    botoes[15] = new JButton("=");
    JButton limpar = new JButton("C");
    limpar.addActionListener(new LimparHandler());
    painelBotoes.add(limpar);
    

    for (JButton botao : botoes) {
      botao.addActionListener(new ButtonHandler());
      painelBotoes.add(botao);
    }

    add(painelBotoes, BorderLayout.CENTER);
  }

  private class ButtonHandler implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
      String text = ((JButton) e.getSource()).getText();
      switch (text) {
        case "0":
        case "1":
        case "2":
        case "3":
        case "4":
        case "5":
        case "6":
        case "7":
        case "8":
        case "9":
          display.setText(display.getText() + text);
          break;
        case ".":
          if (!display.getText().contains(".")) {
            display.setText(display.getText() + text);
          }
          break;
        case "+":
        case "-":
        case "*":
        case "/":
          primeiroNumero = Double.parseDouble(display.getText());
          operacao = text.charAt(0);
          display.setText("");
          break;
        case "=":
          double segundoNumero = Double.parseDouble(display.getText());
          double resultado = 0;
          switch (operacao) {
            case '+':
              resultado = primeiroNumero + segundoNumero;
              break;
            case '-':
              resultado = primeiroNumero - segundoNumero;
              break;
            case '*':
              resultado = primeiroNumero * segundoNumero;
              break;
            case '/':
              if (segundoNumero != 0) {
                resultado = primeiroNumero / segundoNumero;
              } else {
                JOptionPane.showMessageDialog(null, "Erro: divisão por zero!");
              }
              break;
          }
          display.setText(String.valueOf(resultado));
          primeiroNumero = 0;
          operacao = ' ';
          break;
      }
    }
  }
  
  private class LimparHandler implements ActionListener {
    public void actionPerformed(ActionEvent e) {
      display.setText("");
      primeiroNumero = 0;
      operacao = ' ';
    }
  }

  public static void main(String[] args) {
    SwingUtilities.invokeLater(() -> {
      Calculadora calculadora = new Calculadora();
      calculadora.setVisible(true);
    });
  }
}
