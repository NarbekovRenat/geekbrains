package com.geekbrains.firtslesson;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.InvalidParameterException;

@WebServlet(name = "ProductServlet", urlPatterns = "/product")
public class ProductServlet extends HttpServlet {
    private static Logger logger = LoggerFactory.getLogger(ProductServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        logger.info("New GET request");
        // Default product count number
        int prodNumber = 10;
        String prodFromReq = req.getParameter("num");
        if (prodFromReq != null) {
            try{
                prodNumber = Integer.parseInt(prodFromReq);
                if (prodNumber > Product.PRODUCTS.length)
                    throw new IndexOutOfBoundsException("");
                logger.info("Set to return " + prodFromReq + " products");
            }catch (Exception e){
                logger.error("Incorrect product volume!");
                throw new InvalidParameterException("Некоректно введен запрашиваемый размер");
            }
        }else{
            logger.info("No volume, set default " + prodNumber);
        }


        StringBuilder resultString = new StringBuilder();
        for (Product product: Product.getListByVolume(prodNumber)){
            resultString.append(product);
        }

        req.setAttribute("num", prodNumber);
        req.setAttribute("payload", resultString);
        getServletContext().getRequestDispatcher("/product.jsp").forward(req, resp);
    }
}
