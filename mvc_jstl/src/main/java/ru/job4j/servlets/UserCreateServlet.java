package ru.job4j.servlets;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.job4j.logic.Validate;
import ru.job4j.logic.ValidateService;
import ru.job4j.memory.DBStore;
import ru.job4j.model.User;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class UserCreateServlet extends HttpServlet {

    private final Validate validate = ValidateService.getINSTANCE();

    private static final Logger LOG = LogManager.getLogger(DBStore.class.getName());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        req.getRequestDispatcher("/WEB-INF/views/UserCreate.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws  IOException {
        Action add = new AddAction();
        DiskFileItemFactory factory = new DiskFileItemFactory();
        ServletContext servletContext = this.getServletConfig().getServletContext();
        File repository = (File) servletContext.getAttribute("javax.servlet.context.tempdir");
        factory.setRepository(repository);
        ServletFileUpload upload = new ServletFileUpload(factory);
        String id = null;
        String name = null;
        String login = null;
        String email = null;
        String createDate = null;
        String photo = null;
        try {
            List<FileItem> items = upload.parseRequest(req);
            File folder = new File("c:/bin/images");
            if (!folder.exists()) {
                folder.mkdir();
            }
            for (FileItem item : items) {
                if (!item.isFormField()) {
                    photo = item.getName();
                    while (isValidPhoto(photo)) {
                        String[] photoSplit = photo.split("\\.");
                        photo = String.format("%s_1.%s", photoSplit[0], photoSplit[1]);
                    }
                    File file = new File(folder + File.separator + photo);
                    try (FileOutputStream out = new FileOutputStream(file)) {
                        out.write(item.getInputStream().readAllBytes());
                    }
                } else {
                    switch (item.getFieldName()) {
                        case "id":
                            id = item.getString();
                            break;
                        case "name":
                            name = item.getString();
                            break;
                        case "login":
                            login = item.getString();
                            break;
                        case "email":
                            email = item.getString();
                            break;
                        case "createDate":
                            createDate = item.getString();
                            break;
                    }
                }
            }
        } catch (FileUploadException e) {
            LOG.error(e.getMessage(), e);
        }

        User user = new User(id, name, login, email, createDate, photo);

        add.execute(validate, user);

        resp.sendRedirect(String.format("%s/list", req.getContextPath()));
    }

    private boolean isValidPhoto(String filename) {
        boolean result = false;
        List<String> listImage = new ArrayList<>();
        for (User user : validate.findAll()) {
            if (user.getImage().equals(filename)) {
                listImage.add(user.getImage());
            }
        }
        if (listImage.size() > 0) {
            result = true;
        }
        return result;
    }
}
