package design;

import java.util.*;

public class TransactionalKV {
    Map<String, Stack<Integer>> store = new HashMap<>();
    Map<Integer,Stack<String>> transactions = new HashMap<>();
    Map<String,Stack<Integer>> deletedItemsMap = new HashMap<>();
    Map<Integer,Stack<String>> deletedTransactions = new HashMap<>();
    Set<Integer> rolledBackTransactions = new HashSet<>();

    Integer currTransaction = 0;
    Integer latestTransaction = 0;

    public void printvalues(){
        System.out.println("store="+store);
        for(String s : store.keySet()){
            System.out.println("key="+s+" value="+store.get(s));
        }
    }
    public Integer get(String key){
        if(!store.containsKey(key)) return -1;
        return store.get(key).peek();
    }

    public void set(String key, Integer value){
        Stack<Integer> stack = store.getOrDefault(key,new Stack<Integer>());
        stack.push(value);
        store.put(key,stack);

        System.out.println("set method currTransaction="+currTransaction+" latestTransaction="+latestTransaction);
        if(currTransaction > 0){
            Stack<String> txnStack = transactions.getOrDefault(currTransaction,new Stack<String>());
            txnStack.push(key);
            transactions.put(currTransaction,txnStack);
        }
    }

    public void unset(String key){
        System.out.println("unset method currTransaction="+currTransaction+" latestTransaction="+latestTransaction);
        if(currTransaction > 0){
            Stack<Integer> itemstack = store.getOrDefault(key, new Stack<Integer>());
            Stack<String> deletedItems = deletedTransactions.getOrDefault(currTransaction, new Stack<>());
            deletedItems.push(key);
            deletedTransactions.put(currTransaction,deletedItems);
            deletedItemsMap.put(key,itemstack);
        }
        store.remove(key);

    }

    public void begin(){
        System.out.println("begin method currTransaction="+currTransaction+" latestTransaction="+latestTransaction);
        System.out.println("begin method rolledBackTransactions="+rolledBackTransactions);
        while(rolledBackTransactions.contains(currTransaction)){
            currTransaction++;
        }
        System.out.println("begin method after increment currTransaction="+currTransaction+" latestTransaction="+latestTransaction);
        if(currTransaction > latestTransaction) latestTransaction = currTransaction;
        System.out.println("begin method after compare currTransaction="+currTransaction+" latestTransaction="+latestTransaction);
    }

    public void rollback(){
        System.out.println("currTransaction"+currTransaction+" latestTransaction="+latestTransaction);
        while(latestTransaction >= currTransaction){
            if(!rolledBackTransactions.contains(currTransaction)){
                rolledBackTransactions.add(latestTransaction);
                if(deletedTransactions.containsKey(latestTransaction) && !deletedTransactions.get(latestTransaction).isEmpty()){
                    Stack<String> deleted = deletedTransactions.get(latestTransaction);
                    while(!deleted.isEmpty()){
                        String key = deleted.pop();
                        Stack<Integer> newitems = deletedItemsMap.getOrDefault(key, new Stack<>());
                        Stack<Integer> existing = store.getOrDefault(key, new Stack<>());
                        Stack<Integer> temp = new Stack<>();
                        while(!existing.isEmpty()){
                            temp.push(existing.pop());
                        }
                        while(!temp.isEmpty()){
                            newitems.push(temp.pop());
                        }
                        store.put(key,newitems);
                    }
                }

                Stack<String> txn = transactions.getOrDefault(latestTransaction,new Stack<>());
                while(!txn.isEmpty()){
                    String item = txn.pop();
                    Stack<Integer> st = store.getOrDefault(item,new Stack<>());
                    if(!st.isEmpty()) st.pop();
                    store.put(item,st);
                }
            }
            latestTransaction--;

        }
        currTransaction--;
    }

    public void commit(){
        System.out.println("begin commit currTransaction"+currTransaction+" latestTransaction="+latestTransaction);
        System.out.println("begin commit rolledBackTransactions"+rolledBackTransactions);
        while(rolledBackTransactions.contains(currTransaction)){
            currTransaction--;
        }
        System.out.println("end commit currTransaction"+currTransaction+" latestTransaction="+latestTransaction);
    }
    public static void main(String[] args){
        TransactionalKV map = new TransactionalKV();
        String s = map.readLine();
        while(!s.equals("END")) {
            String[] arr = s.split(("\\W"));
            String command = arr[0];
            String key = null;
            if(arr.length > 1 ) key = (arr[1]);
            Integer value = null;
            if(arr.length == 3) value = Integer.valueOf(arr[2]);
            if(command.equals("SET")) {
                map.set(key, value);
            }else if(command.equals("UNSET")) {
                map.unset(key);
            }
            else if(command.equals("BEGIN")) {
                map.begin();
            }else if(command.equals("COMMIT")) {
                map.commit();
            }else if(command.equals("ROLLBACK")){
                map.rollback();
            }
            map.printvalues();
            s = map.readLine();
        }
    }

    private String readLine(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter a command:");
        String str = scanner.nextLine();

        System.out.println("str="+str);
        return str;
    }
}
