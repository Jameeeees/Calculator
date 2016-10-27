package com.james.calculator.States;

import com.james.calculator.Calculator;

/**
 * 对应图中状态1
 */
public class OperandOneWithoutDot implements State {

    Calculator calculator;
    public OperandOneWithoutDot(Calculator calculator) {
        this.calculator = calculator;
    }


    /**
     * 当找到小数点时应当作出的应对
     * 跳到有小数点数字的状态去
     */
    @Override
    public void findDot() {
        calculator.setOperandOne();
        calculator.setCurrentState(calculator.getOperandOneWithDot());
    }

    /**
     * 当找到数字时应当作出的应对
     */
    @Override
    public void findDigit() {
        calculator.setOperandOne();
        calculator.setCurrentState(calculator.getOperandOneWithoutDot());
    }

    /**
     * 当找到运算符时应该作出的应对
     * 先将OP2 = OP1
     * 并设置OP
     * @param operator
     */
    @Override
    public void findOperator(char operator) {
        // TODO: 2016/9/19 something Wrong 
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
        calculator.setCurrentState(calculator.getOperandOneWithoutDot());
    }

    @Override
    public String toString() {
        return "OperandOneWithoutDot{" +
                "calculator=" + calculator +
                '}';
    }
}
