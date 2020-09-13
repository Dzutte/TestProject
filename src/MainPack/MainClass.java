package MainPack;

import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Collections;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MainClass
{
    private static List<Integer> ListX;
    private static List<Integer> ListS;
    private static List<Integer> ListM;

    private static void listsInit()
    {
        ListX = new ArrayList<>();
        ListS = new ArrayList<>();
        ListM = new ArrayList<>();
    }

    private static List<Integer> def;

    /*private static List<Integer> stringToList(String array)
    {
        List<Integer> l = new ArrayList<>();
        while(!array.isEmpty())
        {
            String tmp = "";
            boolean suc = true;
            if(array.contains(","))
            {
                int sepPoint = array.indexOf(",");
                tmp += array.substring(0, sepPoint);
                if(sepPoint < array.length())
                    array = array.substring(array.indexOf(",") + 1);
                else
                    array = "";
            }
            else
            {
                tmp += array;
                array = "";
            }
            if(tmp.length() > 1)
            {
                if(tmp.charAt(0) == ' ')
                    tmp = tmp.substring(1);
                if(tmp.charAt(tmp.length() - 1) == ' ')
                    tmp = tmp.substring(0, tmp.length() - 1);
            }
            int a = 0;
            try
            {
                a = Integer.parseInt(tmp);
            }
            catch(NumberFormatException nfe)
            {
                suc = false;
            }
            if(suc)
                l.add(a);
        }
        return(l);
    }*/

    private static boolean isNumber(char ch)
    {
        return Stream.of('0', '1', '2', '3', '4', '5', '6', '7', '8', '9').anyMatch(c -> c == ch);
    }

    private static List<Integer> stringToList(String array)
    {
        List<Integer> intList = new ArrayList<>();
        boolean notEmptyElement = false;
        int tmpInt = 0;
        for(int i = 0; i < array.length(); i ++)
        {
            if(isNumber(array.charAt(i)))
            {
                tmpInt = tmpInt * 10 + Integer.parseInt(array.substring(i,i+1));
                notEmptyElement = true;
            }
            else
                if(notEmptyElement)
                {
                    intList.add(tmpInt);
                    notEmptyElement = false;
                    tmpInt = 0;
                }
        }
        if(notEmptyElement)
            intList.add(tmpInt);
        return(intList);
    }

    /*private static void checkMod(List<Integer> l, List<Integer> ch, int dev)
    {
        for (int I : ch)
            if ((I % dev) == 0)
                l.add(I);
    }*/

    private static void init(String array)
    {
        def = stringToList(array);
        listsInit();
        //checkMod(ListX, def, 3);
        ListX = def.stream().filter(o -> o % 3 == 0).collect(Collectors.toList());
        //checkMod(ListS, def, 7);
        ListS = def.stream().filter(o -> o % 7 == 0).collect(Collectors.toList());
        //checkMod(ListM, def, 21);
        ListM = def.stream().filter(o -> o % 21 == 0).collect(Collectors.toList());
    }

    private static void print(List<Integer> l, char type)
    {
        Collections.sort(l);
        if(l.isEmpty())
            System.out.println("Список " + type + " пуст");
        else
            for (Object o : l)
                System.out.println(o);
        System.out.println();
    }

    private static void print(char type)
    {
        switch(type)
        {
            case 'X':
                print(ListX, type);
                break;
            case 'S':
                print(ListS, type);
                break;
            case 'M':
                print(ListM, type);
                break;
        }
    }

    private static void print()
    {
        print(ListX, 'X');
        print(ListS, 'S');
        print(ListM, 'M');
    }

    private static void anyMore()
    {
        if(def.stream().anyMatch(I -> (!(ListX.contains(I)) && !(ListS.contains(I)) && !(ListM.contains(I)))))
            System.out.println("true");
        else
            System.out.println("false");
    }

    private static void clear(char type)
    {
        switch(type)
        {
            case 'X':
                ListX.clear();
                break;
            case 'S':
                ListS.clear();
                break;
            case 'M':
                ListM.clear();
                break;
        }
    }

    private static void merge()
    {
        Stream<Integer> stream = Stream.concat(ListX.stream(), ListS.stream());
        stream = Stream.concat(stream, ListM.stream()).distinct();
        print(stream.collect(Collectors.toList()), 'A');
        ListX.clear();
        ListS.clear();
        ListM.clear();
    }

    private static void help()
    {
        System.out.println("init array - инициализация списков набором значений array");
        System.out.println("print - печать всех списков ");
        System.out.println("print type - печать конкретного списка, где type принимает значения X,S,M");
        System.out.println("anyMore - выводит на экран были ли значения не вошедшие ни в один список, возможные значения true, false");
        System.out.println("clear type - чистка списка , где type принимает значения X,S,M");
        System.out.println("merge - слить все списки в один вывести на экран и очистить все списки");
        System.out.println("help - вывод справки по командам");
        System.out.println("exit - выход");
    }

    private static void menu()
    {
        Scanner sc = new Scanner(System.in);
        String s;
        while(true)
        {
            s = sc.nextLine();
            if(s.startsWith("init "))
            {
                if(s.length() > 5)
                    init(s.substring(5));
                else
                    listsInit();
            }
            else if(s.startsWith("print"))
            {
                if(s.length() == 5)
                    print();
                else
                if(s.length() == 7)
                    print(s.charAt(6));
            }
            else if(s.equals("anyMore"))
                anyMore();
            else if(s.startsWith("clear"))
            {
                if(s.length() == 7)
                    clear(s.charAt(6));
            }
            else if(s.equals("merge"))
                merge();
            else if(s.equals("help"))
                help();
            else if(s.equals("exit"))
                break;
        }
    }

    public static void main(String[] args)
    {
        menu();
    }
}
