package lk.practice.candidate_recruitment_tracker.web.interceptors;

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
        String reqMethod = request.getMethod();

        //get req uri
        String uri = request.getRequestURI();

        //take role from header
        String role = request.getHeader("X-User-Role");



         /* DELETE */
        //req delete and role != ADMIN then block
        if("DELETE".equalsIgnoreCase(reqMethod)){
            if(role == null || ! role.trim().equalsIgnoreCase("ADMIN")){
              //throw 403 error
                response.setStatus(HttpServletResponse.SC_FORBIDDEN);
                response.getWriter().write("Access denied: Only ADMIN can delete candidates");

                return false;
            }
        }

        /* STATUS CHANGE */
        //only ADMIN can change status, if not throw and error
        if("PUT".equalsIgnoreCase(reqMethod) && uri.startsWith("/api/v1/candidates/status/")){
            if (role == null || ! role.trim().equalsIgnoreCase("ADMIN")) {
                //throw 403 error
                response.setStatus(HttpServletResponse.SC_FORBIDDEN);
                response.getWriter().write("Access denied: Only ADMIN can update candidate statuses");

                return false;
            }
        }


        return true;
    }
}