package Library;

public class Check {

    //Kiểm tra có phải số nguyên
    public boolean isInteger(String s) {
        try {
            Integer.parseInt(s);
        } catch(NumberFormatException | NullPointerException e) {
            return false;
        }
        return true;
    }

    //Kiểm tra có phải Long
    public boolean isLong(String s){
        try {
            Long.parseLong(s);
        }catch (NumberFormatException e){
            return false;
        }catch (NullPointerException e){
            return false;
        }
        return true;
    }

    //Kiểm tra có phải Date không
    public boolean isDateOrNot(String date){
        String[] dates = date.split("/");
        if(dates.length < 3){
            return false;
        }
        for (String d : dates){
            if (!isInteger(d)){
                return false;
            }
        }
        if(Integer.parseInt(dates[1]) <=0 || Integer.parseInt(dates[1]) > 12){
            return false;
        }
        if (soNgay(Integer.parseInt(dates[1]),Integer.parseInt(dates[2]))<Integer.parseInt(dates[0])
                || Integer.parseInt(dates[0]) <= 0){
            return false;
        }
        return true;
    }
    public int soNgay(int th, int na){
        switch (th) {
            case 1:
            case 3:
            case 5:
            case 7:
            case 8:
            case 10:
            case 12:
                return 31;
            case 4:
            case 6:
            case 9:
            case 11:
                return 30;
            case 2: {
                if (na % 4 == 0) {
                    return 29;
                }
                return 28;
            }
            default: return -1;
        }
    }

    //Money Convert
    public String moneyConvert(String money){
        String s ="";
        for (int i=0; i<money.length() ; i++){
            char a = money.charAt(i);
            if(a != 'v' && a != 'n' && a != 'd' && a != '.' && a != 'V' && a != 'N' && a != 'D'){
                s += a;
            }
        }
        return s;
    }

    //Kiểm tra có phải phép tính không
    public String[] mathAnalysis(String math){
        String[] result = new String[2];
        result[0] = "";
        result[1] = "+";
        for (int i=0; i<math.length() ; i++){
            char a = math.charAt(i);
            switch (a){
                case '+':
                case '-':
                case 'x':
                case '*':
                case '/':
                    result[1] += a;
                    result[0] += " ";
                    break;
                default:
                    if(a >= '0' && a <= '9'){
                        result[0] += a;
                    }
                    break;
            }
        }
        return result;
    }
    public boolean mathCheck(String[] analysisResult){
        String[] s1 = analysisResult[0].trim().split(" ");
        String[] s2 = analysisResult[1].split("");

        if (s1.length != s2.length || analysisResult[0].trim().equals("")){
            return false;
        }

        return true;
    }
    public String matConvert(String[] analysisResult){
        long result = 0;

        String[] s1 = analysisResult[0].split(" ");
        String[] s2 = analysisResult[1].split("");

        for (int i=0 ; i<s2.length;i++){
            switch (s2[i]) {
                case "+" -> result = result + Long.parseLong(s1[i]);
                case "-" -> result = result - Long.parseLong(s1[i]);
                case "x" -> result = result * Long.parseLong(s1[i]);
                case "*" -> result = result * Long.parseLong(s1[i]);
                case "/" -> result = result / Long.parseLong(s1[i]);
            }
        }
        if(result < 0){
            result = 0;
        }
        return Long.toString(result);
    }
}
