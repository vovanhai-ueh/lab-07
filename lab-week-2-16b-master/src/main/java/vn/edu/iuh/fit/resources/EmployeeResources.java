package vn.edu.iuh.fit.resources;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;
import vn.edu.iuh.fit.models.Employee;
import vn.edu.iuh.fit.services.EmployeeServices;

import java.util.List;
import java.util.Optional;

@Path("/employees")
public class EmployeeResources {

    private EmployeeServices employeeServices;

    public EmployeeResources() {
        employeeServices = new EmployeeServices();
    }

    @GET
    @Path(("/{id}"))
    @Produces("application/json")
    public Response getEmp(@PathParam("id") long id) {
        Optional<Employee> emp = employeeServices.findById(id);
        if (emp.isPresent()) {
            return Response.ok(emp).build();
        }
        return Response.status(Response.Status.BAD_REQUEST).build();
    }

    @GET
    @Produces("application/json")
    public Response getAll() {
        List<Employee> lst = employeeServices.getAll();
        return Response.ok(lst).build();
    }

    @POST
    @Produces("application/json")
    @Consumes("application/json")
    public Response insert(Employee employee) {
        //ResponseEntity
        employeeServices.insertEmp(employee);
        return Response.ok(employee).build();
    }


    @DELETE
    @Path(("/{id}"))
    public Response delete(@PathParam("id") long id) {
        if (employeeServices.delete(id))
            return Response.ok().build();
        return Response.status(Response.Status.NOT_FOUND).build();
    }

}
