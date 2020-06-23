package pe.edu.upc.controller;

import java.io.IOException;
import java.net.MalformedURLException;
import java.text.ParseException;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import pe.edu.upc.entity.Project;
import pe.edu.upc.serviceinterface.IUploadFileService;
import pe.edu.upc.serviceinterface.ICategoryService;
import pe.edu.upc.serviceinterface.IProjectService;
import pe.edu.upc.serviceinterface.IStudentService;

@Controller
@RequestMapping("projects")
public class ProjectController {
    @Autowired
    private IProjectService pS;
    
    @Autowired
     private ICategoryService cS; 
    
    @Autowired
    private IStudentService sS;
    
    @Autowired
    private IUploadFileService uploadFileService;
    
  
    @GetMapping("/new")
    public String newProject(Model model) {
        model.addAttribute("listCategories", cS.list());
        model.addAttribute("listStudents", sS.list());
        model.addAttribute("project", new Project());
        return "project/project";
    }
    
    /*@PostMapping("/save")
    public String saveProject(@Validated Project project, BindingResult result, Model model) throws Exception{
        if(result.hasErrors()) {
            model.addAttribute("listCategories", cS.list());
            model.addAttribute("listStudents", sS.list());
            return "project/project";
        }else {
            int rpta=pS.insert(project);
            if(rpta>0) {
                model.addAttribute("mensaje","Ya existe el proyecto");
                model.addAttribute("listCategories",cS.list());
                model.addAttribute("listStudents",sS.list());
                return "project/project";
            }else {
                
                
                /* No sirve: pS.insert(project);*/
                /*model.addAttribute("mensaje","Se guardo correctamente"); no sirve*/
                
                
                /*model.addAttribute("mensaje", "Se guardo correctamente");
                model.addAttribute("listProjects",pS.list());
                return "redirect:/projects/list"; */
            /*}
            
        }
            
        
        
    }*/
    
    @GetMapping("/list")
    public String listProject(Model model) {
        try {
            model.addAttribute("project", new Project());
            model.addAttribute("listProjects",pS.list());
        }catch (Exception e) {
            model.addAttribute("error",e.getMessage());
        }
        return "project/listProjects";
    }
    
    @RequestMapping("/delete/{id}")
 
    public String deleteProject(Model model, @PathVariable(value="id")int id) {
        try {
            if(id>0) {
           pS.delete(id);
    
            model.addAttribute("listProjects",pS.list());
            model.addAttribute("project", new Project());
            model.addAttribute("mensaje","Se elimino el proyecto");
            }
            
			return "project/listProjects";
			
        }catch(Exception e) {
            System.out.println(e.getMessage());
        model.addAttribute("mensaje","Ocurrio error al eliminar");
        model.addAttribute("listProjects",pS.list());
        
        return "project/listProjects";
    }
    }
    
    @RequestMapping("/irupdate/{id}")
  
    public String irUpdate(@PathVariable int id, Model model, RedirectAttributes objRedir) {
        Optional<Project> objPro=pS.searchId(id);
        if(objPro==null){
            objRedir.addFlashAttribute("mensaje","Ocurrio un error");
            return "redirect:/projects/list";
        }else {
            model.addAttribute("listCategories",cS.list());
            model.addAttribute("listStudents",sS.list());
            model.addAttribute("project",objPro.get());
            model.addAttribute("mensaje","Guardar para actualizar");
        return "project/project";
        }
        
    }

    @RequestMapping("/search")
    public String searchCategories(Model model, @Validated Project project) throws ParseException {
        List<Project> listProjects;
        listProjects = pS.search(project.getNameProject());
        if (listProjects.isEmpty()) {
            model.addAttribute("mensaje", "No se encontró");
        }
        model.addAttribute("listProjects", listProjects);

        return "project/listProjects";

    } 
    
    
    @GetMapping(value = "/uploads/{filename:.+}")
    public ResponseEntity<Resource> verFoto(@PathVariable String filename) {

        Resource recurso = null;

        try {
            recurso = uploadFileService.load(filename);
        } catch (MalformedURLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + recurso.getFilename() + "\"")
                .body(recurso);
    }
    
   
    @PostMapping("/save")
    public String saveProduct(@Valid Project project, BindingResult result, Model model,
            @RequestParam("file") MultipartFile foto, RedirectAttributes flash, SessionStatus status) throws Exception {
        if(result.hasErrors()) {
            model.addAttribute("listCategories", cS.list());
            model.addAttribute("listStudents", sS.list());
            return "project/project";
        } else {
            if (!foto.isEmpty()) {

                if (project.getIdProject() > 0 && project.getFoto() != null && project.getFoto().length() > 0) {

                    uploadFileService.delete(project.getFoto());
                }

                String uniqueFilename = null;
                try {
                    uniqueFilename = uploadFileService.copy(foto);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                flash.addFlashAttribute("info", "Has subido correctamente '" + uniqueFilename + "'");
                project.setFoto(uniqueFilename);
            }
            int rpta=pS.insert(project);
            if(rpta>0) {
                model.addAttribute("mensaje","Ya existe el proyecto");
                model.addAttribute("listCategories",cS.list());
                model.addAttribute("listStudents",sS.list());
                return "project/project";
            } else {
                model.addAttribute("mensaje", "Se guardó correctamente");
                status.setComplete();
            }
        }
        model.addAttribute("listaProjects", pS.list());

        return "redirect:/projects/list";
    }

    @GetMapping(value = "/view/{id}")
    public String ver(@PathVariable(value = "id") Integer id, Model model, RedirectAttributes flash) {

        Optional<Project> project = pS.searchId(id);
        if (project == null) {
            flash.addFlashAttribute("error", "El proyecto no existe en la base de datos");
            return "redirect:/projects/list";
        }

        model.addAttribute("project", project.get());

        return "project/view";
    }
    
    
}

