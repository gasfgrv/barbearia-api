
ALTER TABLE
  "public"."barbeiro"
ADD
  CONSTRAINT "barbeiro_fkey" FOREIGN KEY ("login") REFERENCES "public"."usuario" ("login");