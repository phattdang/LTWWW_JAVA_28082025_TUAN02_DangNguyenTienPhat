package fit.iuh.se.ltwww_java_28082025_tuan02_dangnguyentienphat.baitap2;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@WebServlet(name = "fileUploadServlet", urlPatterns = {"/file-upload"})
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024,
        maxFileSize = 1024 * 1024 * 5,
        maxRequestSize = 1024 * 1024 * 10
)
public class FileUploadServlet extends HttpServlet {
    private String uploadPath = "/uploads";
    private String uploadPathToSource;
    private String uploadPathToTarget;

    @Override
    public void init() throws ServletException {
        String userDir = System.getProperty("user.dir");
        if (userDir.contains("tomcat") && userDir.endsWith("bin")) {
            userDir = new File(userDir).getParent();
            userDir = new File(userDir).getParent();
        }
        uploadPathToSource = userDir + "/LTWWW_JAVA_28082025_TUAN02_DangNguyenTienPhat/src/main/webapp/uploads";
        File uploadPathToSourceDir = new File(uploadPathToSource);
        if (!uploadPathToSourceDir.exists()) {
            uploadPathToSourceDir.mkdirs();
        }

        uploadPathToTarget = this.getServletContext().getRealPath("/uploads");
        File uploadPathToTargetDir = new File(uploadPathToTarget);
        if (!uploadPathToTargetDir.exists()) {
            uploadPathToTargetDir.mkdirs();
        }

        System.out.println("uploadPathToSource: " + uploadPathToSource);
        System.out.println("uploadPathToTarget: " + uploadPathToTarget);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("Parts count: " + req.getParts().size());

        for (Part filePart : req.getParts()) {
            if (filePart == null || filePart.getSubmittedFileName() == null || filePart.getSubmittedFileName().isEmpty()) {
                System.out.println("Skipping invalid part: " + (filePart != null ? filePart.getName() : "null"));
                continue;
            }

            String fileName = filePart.getSubmittedFileName();
            System.out.println("Processing file: " + fileName);

            try {
                byte[] fileContent = filePart.getInputStream().readAllBytes();

                Files.write(Paths.get(uploadPathToSource + File.separator + fileName), fileContent);
                System.out.println("Saved to source: " + uploadPathToSource + File.separator + fileName);

                Files.write(Paths.get(uploadPathToTarget + File.separator + fileName), fileContent);
                System.out.println("Saved to target: " + uploadPathToTarget + File.separator + fileName);
            } catch (IOException e) {
                System.err.println("Error saving file " + fileName + ": " + e.getMessage());
                throw new ServletException("Failed to save file: " + fileName, e);
            }
        }
    }
}