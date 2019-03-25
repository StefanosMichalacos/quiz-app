select *
from "user" inner join "user_role" on "user".id = "user_role".id
where "value" = 'ADMINISTRATOR';

