-- 1. 사용자
CREATE TABLE users (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  nickname VARCHAR(50),
  create_date DATETIME DEFAULT CURRENT_TIMESTAMP,
  update_date DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  delete_date DATETIME NULL,
  create_id BIGINT,
  update_id BIGINT,
  delete_id BIGINT
);

-- 2. 심리검사 정의
CREATE TABLE tests (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  title VARCHAR(100) NOT NULL,
  description TEXT,
  create_date DATETIME DEFAULT CURRENT_TIMESTAMP,
  update_date DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  delete_date DATETIME NULL,
  create_id BIGINT,
  update_id BIGINT,
  delete_id BIGINT
);

-- 3. 문항
CREATE TABLE questions (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  test_id BIGINT NOT NULL,
  text TEXT NOT NULL,
  dimension VARCHAR(50),
  create_date DATETIME DEFAULT CURRENT_TIMESTAMP,
  update_date DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  delete_date DATETIME NULL,
  create_id BIGINT,
  update_id BIGINT,
  delete_id BIGINT
);

-- 4. 선택지
CREATE TABLE choices (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  question_id BIGINT NOT NULL,
  text VARCHAR(200) NOT NULL,
  score_delta INT NOT NULL,
  create_date DATETIME DEFAULT CURRENT_TIMESTAMP,
  update_date DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  delete_date DATETIME NULL,
  create_id BIGINT,
  update_id BIGINT,
  delete_id BIGINT
);

-- 5. 검사 세션
CREATE TABLE test_sessions (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  session_id CHAR(36) NOT NULL UNIQUE,
  user_id BIGINT,
  test_id BIGINT NOT NULL,
  started_at DATETIME DEFAULT CURRENT_TIMESTAMP,
  finished_at DATETIME,
  create_date DATETIME DEFAULT CURRENT_TIMESTAMP,
  update_date DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  delete_date DATETIME NULL,
  create_id BIGINT,
  update_id BIGINT,
  delete_id BIGINT
);

-- 6. 응답
CREATE TABLE answers (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  session_id BIGINT NOT NULL,
  question_id BIGINT NOT NULL,
  choice_id BIGINT NOT NULL,
  create_date DATETIME DEFAULT CURRENT_TIMESTAMP,
  update_date DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  delete_date DATETIME NULL,
  create_id BIGINT,
  update_id BIGINT,
  delete_id BIGINT
);

-- 7. 점수 상태
CREATE TABLE score_states (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  session_id BIGINT NOT NULL,
  dimension VARCHAR(50) NOT NULL,
  score INT NOT NULL,
  update_date DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  create_date DATETIME DEFAULT CURRENT_TIMESTAMP,
  delete_date DATETIME NULL,
  create_id BIGINT,
  update_id BIGINT,
  delete_id BIGINT
);

-- 8. 문항 큐
CREATE TABLE queue_items (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  session_id BIGINT NOT NULL,
  question_id BIGINT NOT NULL,
  position INT NOT NULL,
  is_answered BOOLEAN DEFAULT FALSE,
  create_date DATETIME DEFAULT CURRENT_TIMESTAMP,
  update_date DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  delete_date DATETIME NULL,
  create_id BIGINT,
  update_id BIGINT,
  delete_id BIGINT
);

-- 9. 룰
CREATE TABLE rules (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  test_id BIGINT NOT NULL,
  priority INT NOT NULL,
  stop_after_apply BOOLEAN DEFAULT FALSE,
  create_date DATETIME DEFAULT CURRENT_TIMESTAMP,
  update_date DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  delete_date DATETIME NULL,
  create_id BIGINT,
  update_id BIGINT,
  delete_id BIGINT
);

-- 10. 룰 조건
CREATE TABLE rule_conditions (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  rule_id BIGINT NOT NULL,
  condition_type VARCHAR(50) NOT NULL,
  dimension VARCHAR(50),
  target_dimension VARCHAR(50),
  operator VARCHAR(10) NOT NULL,
  value INT NOT NULL,
  create_date DATETIME DEFAULT CURRENT_TIMESTAMP,
  update_date DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  delete_date DATETIME NULL,
  create_id BIGINT,
  update_id BIGINT,
  delete_id BIGINT
);

-- 11. 룰 액션
CREATE TABLE rule_actions (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  rule_id BIGINT NOT NULL,
  action_type VARCHAR(20) NOT NULL,
  question_id BIGINT NOT NULL,
  extra_data TEXT,
  create_date DATETIME DEFAULT CURRENT_TIMESTAMP,
  update_date DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  delete_date DATETIME NULL,
  create_id BIGINT,
  update_id BIGINT,
  delete_id BIGINT
);




-- FK �߰�
ALTER TABLE test_sessions ADD CONSTRAINT fk_test_sessions_user FOREIGN KEY (user_id) REFERENCES users(id);
ALTER TABLE test_sessions ADD CONSTRAINT fk_test_sessions_test FOREIGN KEY (test_id) REFERENCES tests(id);

ALTER TABLE questions ADD CONSTRAINT fk_questions_test FOREIGN KEY (test_id) REFERENCES tests(id) ON DELETE CASCADE;
ALTER TABLE choices ADD CONSTRAINT fk_choices_question FOREIGN KEY (question_id) REFERENCES questions(id) ON DELETE CASCADE;

ALTER TABLE answers ADD CONSTRAINT fk_answers_session FOREIGN KEY (session_id) REFERENCES test_sessions(id) ON DELETE CASCADE;
ALTER TABLE answers ADD CONSTRAINT fk_answers_question FOREIGN KEY (question_id) REFERENCES questions(id);
ALTER TABLE answers ADD CONSTRAINT fk_answers_choice FOREIGN KEY (choice_id) REFERENCES choices(id);

ALTER TABLE score_states ADD CONSTRAINT fk_score_states_session FOREIGN KEY (session_id) REFERENCES test_sessions(id) ON DELETE CASCADE;

ALTER TABLE queue_items ADD CONSTRAINT fk_queue_items_session FOREIGN KEY (session_id) REFERENCES test_sessions(id) ON DELETE CASCADE;
ALTER TABLE queue_items ADD CONSTRAINT fk_queue_items_question FOREIGN KEY (question_id) REFERENCES questions(id);

ALTER TABLE rules ADD CONSTRAINT fk_rules_test FOREIGN KEY (test_id) REFERENCES tests(id) ON DELETE CASCADE;

ALTER TABLE rule_conditions ADD CONSTRAINT fk_rule_conditions_rule FOREIGN KEY (rule_id) REFERENCES rules(id) ON DELETE CASCADE;
ALTER TABLE rule_actions ADD CONSTRAINT fk_rule_actions_rule FOREIGN KEY (rule_id) REFERENCES rules(id) ON DELETE CASCADE;
ALTER TABLE rule_actions ADD CONSTRAINT fk_rule_actions_question FOREIGN KEY (question_id) REFERENCES questions(id);
