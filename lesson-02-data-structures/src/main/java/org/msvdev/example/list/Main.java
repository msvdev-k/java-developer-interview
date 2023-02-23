package org.msvdev.example.list;

public class Main {
    public static void main(String[] args) {

        List<String> linkedList = new LinkedList<>();
        List<String> arrayList = new ArrayList<>();

        linkedList.append("str 00");
        linkedList.append("str 01");
        linkedList.append("str 02");
        linkedList.append("str 03");
        linkedList.append("str 04");

        arrayList.append("STR 00");
        arrayList.append("STR 01");
        arrayList.append("STR 02");
        arrayList.append("STR 03");
        arrayList.append("STR 04");

        System.out.println(linkedList);
        System.out.println(arrayList);

        System.out.println(linkedList.get(3));
        System.out.println(arrayList.get(3));

        linkedList.remove("str 02");
        linkedList.append("str 05");

        arrayList.remove("STR 02");
        arrayList.append("STR 05");

        System.out.println(linkedList);
        System.out.println(arrayList);
    }
}
