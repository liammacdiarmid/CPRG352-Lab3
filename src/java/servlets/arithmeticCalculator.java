package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class arithmeticCalculator extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/WEB-INF/arithmeticcalculator.jsp").forward(request, response);

        request.setAttribute("result", "Result: ---");

        return;
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String first = request.getParameter("first");
        String second = request.getParameter("second");
        String op = request.getParameter("op");

        if (first == null || first.equals("") || second == null || second.equals("")) {
            request.setAttribute("result", "Result: invalid");
            getServletContext().getRequestDispatcher("/WEB-INF/arithmeticcalculator.jsp").forward(request, response);
            request.setAttribute("first", first);
            request.setAttribute("second", second);

            return;
        } else if (!isInteger(first) || !isInteger(second)) {
            request.setAttribute("result", "Result: invalid");
            getServletContext().getRequestDispatcher("/WEB-INF/arithmeticcalculator.jsp").forward(request, response);
            request.setAttribute("first", first);
            request.setAttribute("second", second);

            return;
        }

        int res = 0, x = Integer.parseInt(first), y = Integer.parseInt(second);

        switch (op) {
            case "+":
                res = x + y;
                break;
            case "-":
                res = x - y;
                break;
            case "*":
                res = x * y;
                break;
            case "%":
                res = x % y;
                break;
            default:
                break;
        }

        request.setAttribute("result", String.format("Result: %d", res));
        getServletContext().getRequestDispatcher("/WEB-INF/arithmeticcalculator.jsp").forward(request, response);

        return;
    }

    public static boolean isInteger(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}