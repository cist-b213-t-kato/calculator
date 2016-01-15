package application;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import calculator.RPN;

public class SampleController {
	// 表示ディスプレイ
	@FXML
	private TextArea display;

	// 数字入れるList
	private BigDecimal[] left = new BigDecimal[100];

	// 演算子入れるList
	private String[] selectoperator = new String[100];

	// 入れた回数カウントするやつ
	private int sumcount;
	private int oprcount;

	//計算結果
	private BigDecimal sum;

	 //コンストラクタ
	public SampleController() {
		this.sumcount = 0;
		this.oprcount = 0;
	}

	StringBuilder sb = new StringBuilder();
	String result = "";

	// ボタン押された時のEvent

	@FXML
	public void anybuttonClicked(ActionEvent e) {

		Button button = (Button) e.getSource();


		if (button.getText().equals("C") || button.getText().equals("AC")) {
			sb.setLength(0);
			display.setText(sb.toString());
		}else if (button.getText().matches("[0-9\\.]")) {
			sb.append(button.getText());
			display.setText(sb.toString());
		}else if(button.getText().matches("[(]")){
			sb.append(button.getText());
			sb.append(" ");
			display.setText(sb.toString());
		}else if(button.getText().matches("[)]")){
			sb.append(" ");
			sb.append(button.getText());
			display.setText(sb.toString());
		}else if(button.getText().equals("Ans")){
			if(!"".equals(result)){
				sb.append(result);
				display.setText(sb.toString());
			}
		}else if (button.getText().matches("[＋－×÷]")) {
			if(sb.length()==0){
				sb.append(result);
				display.setText(sb.toString());
			}

			String buf = "";
			if("＋".equals(button.getText())){
				buf = "+";
			}else if("－".equals(button.getText())){
				buf = "-";
			}else if("×".equals(button.getText())){
				buf = "*";
			}else if("÷".equals(button.getText())){
				buf = "/";
			}
			sb.append(" ");
			sb.append(buf);
			sb.append(" ");
			display.setText(sb.toString());
		}else if (button.getText().matches("=")) {

			try{
				result = RPN.calc(new ArrayList<>(Arrays.asList(sb.toString().split(" ")))).toString();
				display.setText(result);
			}catch(Exception e2){
				display.setText("式が不正じゃなイカ？");
			}
			sb.setLength(0);

		}


	}
//
//
//	public String calculate() {
//		for (int i = 0; i < oprcount; i++) {
//			if (selectoperator[i].equals("÷")) {
//				selectoperator[i]="";
//				try {
//					left[i] = left[i].divide(left[i + 1]);
//				} catch (ArithmeticException e) {
//					left[i] = left[i].divide(left[i + 1], 4,
//							BigDecimal.ROUND_HALF_UP);
//				}
//				for (int j = i + 1; j < sumcount; j++) {
//					left[j] = left[j + 1];
//				}
//			}
//		}
//
//		for (int i = 0; i < oprcount; i++) {
//			if (selectoperator[i].equals("×")) {
//				left[i] = left[i].multiply(left[i + 1]);
//
//				for (int j = i + 1; j < sumcount; j++) {
//					left[j] = left[j + 1];
//				}
//			}
//		}
//
//		for (int i = 0; i < oprcount; i++) {
//			if (selectoperator[i].equals("+")) {
//				left[i] = left[i].add(left[i + 1]);
//
//				for (int j = i + 1; j < sumcount; j++) {
//					left[j] = left[j + 1];
//				}
//			}
//
//			if (selectoperator[i].equals("-")) {
//				left[i] = left[i].subtract(left[i + 1]);
//
//				for (int j = i + 1; j < sumcount; j++) {
//					left[j] = left[j + 1];
//				}
//			}
//		}
//
//		for(int i=0;i<oprcount;i++){
//			selectoperator[i]="";
//		}
//
//		sum = left[0];
//		sumcount = 1;
//		oprcount = 1;
//		return sum.toString();
//
//	}

}

