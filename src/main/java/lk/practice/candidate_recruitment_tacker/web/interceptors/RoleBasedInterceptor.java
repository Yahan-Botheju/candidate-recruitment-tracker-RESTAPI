package lk.practice.candidate_recruitment_tacker.web.interceptors;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.jspecify.annotations.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class RoleBasedInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(
            HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull Object handler
    ) throws Exception{

        //get req method
        String getMethod = request.getMethod();

        //take role from header
        String role = request.getHeader("X-User-Role");

        //req delete and role != ADMIN then block
        if("DELETE".equalsIgnoreCase(getMethod)){
            if(role == null || role.equalsIgnoreCase("ADMIN")){
              //throw 403 error
                response.setStatus(HttpServletResponse.SC_FORBIDDEN);
                response.getWriter().write("Access denied: Only ADMIN can delete candidates");

                return false;
            }
        }
        return true;
    }
}