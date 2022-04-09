package polish;

import java.util.*;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class PolishConvertor {

    private List<String> result = new ArrayList<>();
    private Stack<Character> stack = new Stack<>();
    private Set<Character> operands = new HashSet<>(Arrays.asList('+','-','/','*','^'));
    private List<Character> number = new ArrayList<>();

    public List<String> convert(String expression) {
        if (expression == null || expression.length()==0) {
            return Collections.emptyList(); //возращает пустой лист
        }
        char[] characters = expression.toCharArray();
        //Stream.of(characters).forEach(symbol -> {
        //});
        for(char c: characters)
        {
            if(isDigit(c))
            {
                processDigit(c, number);
                continue;
            }
            if(isBracket(c))
            {
                collectNumberAndPushToResult(number,result);
                processBracket(c, stack, result);
                continue;
            }
            if(isOperator(c))
            {
                collectNumberAndPushToResult(number,result);
                processOperation(c, stack, result);
            }
        }
        collectNumberAndPushToResult(number,result);
        //.map(x->' '+x)
        //.map(x -> new StringBuilder(x).reverse())
        result.add(stack.stream().map(Objects::toString).map(x->x+" ").);
        return result;
    }

    //region Check Type
    boolean isDigit(char symbol){
        return Character.isDigit(symbol);
    }

    boolean isBracket(char symbol)
    {
        return '('==symbol || ')'==symbol;
    };

    boolean isOperator(char symbol)
    {
        return operands.contains(symbol);
    };
    //endregion

    Integer priorityOperation(char symbol)
    {
        if (symbol == '^')
            return 3;
        if (symbol == '*' || symbol == '/')
            return 2;
        else
            return 1;
    }

    void processDigit(char symbol, List<Character> number) {
        number.add(symbol);
    };
    void processBracket(char symbol, Stack<Character> stack, List<String> result) {};
    void processOperation(char symbol, Stack<Character> stack, List<String> result) {
        if (!stack.empty() && (priorityOperation(stack.peek()) >= priorityOperation(symbol)))
        {
            result.add(String.valueOf(stack.pop()));
        }
        stack.add(symbol);
    };

    void collectNumberAndPushToResult(List <Character> number, List<String> result){
        if (number == null || number.isEmpty())
        {
            return;
        }
        result.add(number.stream().map(String::valueOf).collect(Collectors.joining()));
        number.clear();
    }


}
