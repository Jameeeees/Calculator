package com.james.calculator.States;

import com.james.calculator.Calculator;

/**
 * 对应图中状态2
 */
public class OperandOneWithDot implements State {

    Calculator calculator;
    public OperandOneWithDot(Calculator calculator) {
        this.calculator = calculator;
    }


    /**
     * 当找到小数点时应当作出的应对
     * 此时两个小数点，直接报错
     */
    @Override
    public void findDot() {
        calculator.showError();
        calculator.setCurrentState(calculator.getErrorState());
    }

    /**
     * 当找到数字时应当作出的应对
     */
    @Override
    public void findDigit() {
        calculator.setOperandOne();
        calculator.setCurrentState(calculator.getOperandOneWithDot());
    }

    /**
     * 当找到运算符时应该作出的应对
     * 先将OP2 = OP1
     * 并设置OP
     * @param operator
     */
    @Override
    public void findOperator(char operator) {
        calculator.setOperandTwoWithOperandOne();
        calculator.setOperator(operator);
        calculator.appendOperand(String.valueOf(calculator.getOperandOne()));
        calculator.appendOperator(operator);
        calculator.clearInput();
        calculator.setCurrentState(calculator.getOperatorState());
    }

    /**
     * 当按下CE时应作出的应对
     */
    @Override
    public void onCEPressed() {
        calculator.resetAll();
        calculator.setCurrentState(calculator.getInitState());
    }

    /**
     * 当按下C时应作出的应对
     */
    @Override
    public void onCPressed() {
        calculator.resetAll();
        calculator.setCurrentState(calculator.getInitState());
    }

    /**
     * 当按下等号时作出的应对
     * 应该维持在此状态
     * 若Result栏不为空则显示当前输入的数字
     */
    @Override
    public void onEqualPressed() {
        if (calculator.getResult().getText().toString().equals("")){
            calculator.appendOperand(String.valueOf(calculator.getOperandOne()));
        }
        calculator.setCurrentState(calculator.getOperandOneWithDot());
    }

    @Override
    public String toString() {
        return "OperandOneWithDot{" +
                "calculator=" + calculator +
                '}';
    }
}
