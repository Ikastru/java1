package ru.progwards.java1.lessons.bigints;

public class AbsInteger {


    public static AbsInteger add(AbsInteger num1, AbsInteger num2){
        String str1 = num1.toString().trim();
        String str2 = num2.toString().trim();
        int c = Integer.parseInt(str1);
        int d = Integer.parseInt(str2);
        int q = c+d;
        AbsInteger result = new AbsInteger();
        if (q>-128 && q<127 ){
            ByteInteger bint = new ByteInteger((byte) q);
            result= bint;
        } else if(q>-32768 && q<32767) {
            ShortInteger bint = new ShortInteger((short) q);
            result= bint;
        } else  {
            IntInteger bint = new IntInteger(q);
            result= bint;
        }
        return result;
    }

//       String str1 = " "+num1;
//       String str2 = " "+num2;
//       String strArr1[] = str1.split(" ");
//       String strArr2[] = str2.split(" ");
//       byte numArr1[] = new byte[strArr1.length];
//       byte numArr2[] = new byte[strArr2.length];
//       byte numArr[] = new byte[0];
//       if (strArr1.length == strArr2.length){
//           for (int i =strArr1.length-1; i>0; i-- ){
//               numArr[i] = (byte) (numArr1[i] + numArr2[i]);
//           }
//       } else if (strArr1.length > strArr2.length){
//           for (int i =strArr1.length-1; i>0; i-- ){
//               numArr[i] = (byte) (numArr1[i] + numArr2[i]);
//           }
//       } else if (strArr1.length < strArr2.length){
//           for (int i =strArr2.length-1; i>0; i-- ){
//               numArr[i] = (byte) (numArr1[i] + numArr2[i]);
//           }
//       }
//       String str = numArr.toString();
//       byte bn = Byte.parseByte(str);
//       ByteInteger byInt1 = new ByteInteger(bn);
//       return byInt1;
//    }

    public static void main(String[] args) {
        ShortInteger shortInteger = new ShortInteger((short) 20);
        ByteInteger byteInteger = new ByteInteger((byte) 100);
        System.out.println(AbsInteger.add(shortInteger, byteInteger));

    }

}

