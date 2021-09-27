import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

import static java.lang.Double.parseDouble;

public class Program {
    static ArrayList<Beer> list= new ArrayList();
    public static void main(String[] args){
        Beer b1;
        b1 = new Beer("Gösser", "világos", 5.6);
        Beer b2;
        b2 = new Beer("Dreher", "világos", 5.5);
        System.out.println(b1.toString());
        System.out.println(b2.toString());
        InputStreamReader is=new InputStreamReader(System.in);
        BufferedReader br=new BufferedReader(is);
        String line=null;
        while(true){
            try{
                line= br.readLine();
            }
            catch(IOException e){
                System.out.println("Sikertelen beolvasás!");
            }
            String[] commands=line.split(" ");
            //System.out.println(commands[0]+" "+commands.length);
            if(commands[0].equals("exit")){
                System.exit(0);
            }
            else if(commands[0].equals("add")){
                try{
                    add(commands);
                }
                catch(Throwable e){
                    System.out.println("Sikertelen vegrehajtas");
                }
            }
            else if(commands[0].equals("list")){
                try{
                    list(commands);
                }
                catch(Throwable e){
                    System.out.println("Sikertelen vegrehajtas");
                }
            }
            else if(commands[0].equals("save")){
                save(commands);
            }
            else if(commands[0].equals("load")){
                load(commands);
            }
            else if(commands[0].equals("search")){
                search(commands);
            }
            else if(commands[0].equals("find")){
                find(commands);
            }
            else if(commands[0].equals("delete")){
                delete(commands);
            }
        }
    }

    protected static void delete(String[] commands) {
        Iterator<Beer> i=list.iterator();
        while(i.hasNext()){
            if(i.next().getName().equals(commands[1])){
                i.remove();
            }
        }
    }

    protected static void find(String[] commands) {
        for(Beer i : list){
            if(i.getName().contains(commands[1])){
                System.out.println(i.toString());
            }
        }
    }

    protected static void search(String[] commands) {
        for(Beer i : list){
            if(i.getName().equals(commands[1])){
                System.out.println(i.toString());
            }
        }
    }

    protected static void list(String[] commands) {
        if(commands[1].equals("name")){
            Collections.sort(list, new NameComparator());
        }
        else if(commands[1].equals("style")){
            Collections.sort(list, new StyleComparator());
        }
        else if(commands[1].equals("strength")){
            Collections.sort(list, new StrengthComparator());
        }
        Iterator<Beer> i=list.iterator();
        while(i.hasNext()){
            System.out.println(i.next().toString());
        }
    }

    protected static void add(String[] commands) {
        double percent=parseDouble(commands[3]);
        Beer b1=new Beer(commands[1], commands[2], percent);
        list.add(b1);
    }
    protected static void save(String[] commands){
        try{
            FileOutputStream f=new FileOutputStream(commands[1]);
            ObjectOutputStream out=new ObjectOutputStream(f);
            out.writeObject(list);
            out.close();
        }
        catch(IOException e){
            System.out.println("Nem sikerült menteni");
        }

    }
    protected static void load(String[] commands){
        try{
            FileInputStream f=new FileInputStream(commands[1]);
            ObjectInputStream in=new ObjectInputStream(f);
            list=(ArrayList<Beer>)in.readObject();
            in.close();
        }
        catch(IOException | ClassNotFoundException e){
            System.out.println("Nem sikerült betölteni");
        }
    }
}
