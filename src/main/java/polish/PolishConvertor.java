package polish;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class PolishConvertor {

    private List<String> result = new ArrayList<>();
    private Stack<Character> stack = new Stack();
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
        result.add(stack.stream().map(x->new StringBuilder(x).reverse()).map(String::valueOf).collect(Collectors.joining()));
        return Collections.emptyList();
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

    void processDigit(char symbol, List<Character> number) {
        number.add(symbol);
    };
    void processBracket(char symbol, Stack<Character> stack, List<String> result) {};
    void processOperation(char symbol, Stack<Character> stack, List<String> result) {
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
