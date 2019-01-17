package com.imooc.day02_Stack;

public class MySolution {
    public Boolean isValid(String s) {

        ArrayStack<Character> stack = new ArrayStack<Character>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            //只要有反括号，就去弹栈反查前面的符号是否与之闭合，闭合的进行下个符号的判断，非闭合或者前面没有符号的返回false
            if (c == '}' || c == ')' || c == ']') {
                if (stack.isEmpty()) {
                    return false;
                }
                char topChar = stack.pop();
                if (c == '}' && topChar != '{') {
                    return false;
                } else if (c == ')' && topChar != '(') {
                    return false;
                } else if (c == ']' && topChar != '[') {
                    return false;
                }
            } else {
                stack.push(c);
            }
        }
        return stack.isEmpty();
    }

    public static void main(String[] args) {
        System.out.println(new MySolution().isValid("{}"));
        System.out.println(new MySolution().isValid("{]"));
    }
}
