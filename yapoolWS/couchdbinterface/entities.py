
#-------------------------User---------------------------
from couchdb.mapping import *
import dblayer
from dblayer import getDb

#TODO add topics field
class User(Document) :
  login = TextField()
  password = TextField()
  email = TextField()
  activationCode = TextField()
  isActivated = BooleanField()
  type = TextField()
  TYPE = 'user'
 
    
  
  def create(self) :
  #1: create the user in couch
  #2: turn it into a yapooler(add role)
  #3: return a boolean, True if it worked, false otherwise
    if self.findByLogin() == None:
      if self.login and self.password :
        self.createUserInCouch()
        '''
        self.isActivated = False
        self.type = self.TYPE
        self.owner = self.login
        self.store(getDb())
        '''
        #TODO: check if it return the last version
        return True
      else : 
        return False
    else :
      print 'a user already exist for login: ', self.login
      return False
  
      
  def update(self) :
    '''
    update the user, only if he already exist
    '''  
    if self.id :
      self.store(getDb())
    else :
      print 'invalid state, attemp to update a non existing user'
      raise  IllegalAttempt



  def findByLogin(self) :
    '''
    return the actual version of the user.
    '''
    view = dblayer.view("user/login", self.login)
    if len(view) == 0 :
      return None
    elif len(view) == 1:
      #TODO optimize
      for u in view : return User.load(getDb(), u.id)
    else :
      print 'WARNING: critical error, more than one user for same login'
      raise IntegrityConstraintException

  def findByActivationCode(self) :
    view = dblayer.view('user/activationCode', self.activationCode)
    
    if len(view) == 0 :
      return None
    elif len(view) == 1:
      for u in view : return User.load(getDb(), u.id)
    else :
      print 'WARNING: critical error, more than one user with same activation Code '
      raise IntegrityConstraintException
      
  def findBySessionId(self) :
    view = dblayer.view("user/sessionId", self.sessionId)
    if len(view) == 0 :
      return None
    elif len(view) == 1:
      for u in view : return User.load(getDb(), u.id)
    else :
      print 'WARNING: critical error, more than one user with sthe same session Id '
      raise IntegrityConstraintException



class IllegalAttempt(Exception) :
  pass

